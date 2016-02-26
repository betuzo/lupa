package com.codigoartesanal.lupa.services.impl;

import com.codigoartesanal.lupa.model.TipoToken;
import com.codigoartesanal.lupa.model.User;
import com.codigoartesanal.lupa.model.UserRole;
import com.codigoartesanal.lupa.model.UserToken;
import com.codigoartesanal.lupa.repositories.UserRepository;
import com.codigoartesanal.lupa.repositories.UserRoleRepository;
import com.codigoartesanal.lupa.repositories.UserTokenRepository;
import com.codigoartesanal.lupa.services.MailService;
import com.codigoartesanal.lupa.services.UserService;
import com.codigoartesanal.lupa.services.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.codigoartesanal.lupa.model.TipoToken.*;

/**
 * Created by betuzo on 07/04/15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserTokenRepository userTokenRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserTokenService userTokenService;

    @Autowired
    MailService mailService;

    @Autowired
    SimpleMailMessage templateMessage;

    @Override
    public Map<String, Object> findByUsername(String username) {
        return convertUserToMap(userRepository.findByUsername(username));
    }

    @Override
    public Map<String, Object> createUser(Map<String, String> userMap) {
        User user = new User();
        if (userMap.get(PROPERTY_PASSWORD).equals(userMap.get(PROPERTY_PASSWORD_CONFIRM))){
            userMap.put(PROPERTY_ENABLED, String.valueOf(Boolean.FALSE));
            user = userRepository.save(convertMapToUser(userMap));
            TipoToken tipoToken = (get(user.getUsername())==null) ? VALID_EMAIL : CHANGE_PASSWORD;
            userRoleRepository.save(generateRoleDefaultByUser(user));
            UserToken userToken = userTokenService.createUserTokenByUserAndTipo(user, tipoToken);
            sendMailToken(userToken, userMap.get(PROPERTY_CONTEXT));
        }
        return convertUserToMap(user);
    }

    @Override
    public Map<String, Object> changePassword(Map<String, String> userMap) {
        User user = new User();
        Map<String, Object> userTokenMap = userTokenService.userTokenByIdAndTipo(
                userMap.get(UserTokenService.PROPERTY_TOKEN), TipoToken.CHANGE_PASSWORD);
        userMap.put(PROPERTY_USERNAME, (String) userTokenMap.get(PROPERTY_USERNAME));
        if (userMap.get(PROPERTY_PASSWORD).equals(userMap.get(PROPERTY_PASSWORD_CONFIRM))){
            userMap.put(PROPERTY_ENABLED, String.valueOf(Boolean.TRUE));
            user = userRepository.save(convertMapToUser(userMap));
        }
        return convertUserToMap(user);
    }

    private Map<String, Object> convertUserToMap(User user){
        Map<String, Object> sessionDTO = new HashMap<String, Object>();
        sessionDTO.put(PROPERTY_ROLES, getRolesByUser(user));
        sessionDTO.put(PROPERTY_ID, String.valueOf(user.getUsername()));
        sessionDTO.put(PROPERTY_USERNAME, user.getUsername());
        return sessionDTO;
    }

    private User convertMapToUser(Map<String, String> userMap) {
        User user = new User();
        user.setUsername(userMap.get(PROPERTY_USERNAME));
        user.setPassword(userMap.get(PROPERTY_PASSWORD));
        user.setEnabled(Boolean.parseBoolean(userMap.get(PROPERTY_ENABLED)));
        return user;
    }

    private List<String> getRolesByUser(User user){
        user.setUserRole(userRoleRepository.findAllByUser(user));
        Iterator<UserRole> iter = user.getUserRole().iterator();
        List<String> roles = new ArrayList<String>();
        while (iter.hasNext()) {
            UserRole userRole = iter.next();
            roles.add(userRole.getRole());
        }
        return roles;
    }

    private UserRole generateRoleDefaultByUser(User user) {
        UserRole role = new UserRole();
        role.setUser(user);
        role.setRole(PROPERTY_ROLE_DEFAULT);
        return role;
    }

    private User get(String username){
        return userRepository.findOne(username);
    }

    private void sendMailToken(UserToken userToken, String context) {
        templateMessage.setTo(userToken.getUser().getUsername());
        templateMessage.setCc(userToken.getUser().getUsername());

        Map<String, Object> props = new HashMap<>();
        props.put("action", userToken.getTipo().getDescription());
        props.put("link", context + "#token/" + userToken.getToken());

        mailService.send(templateMessage, props);
    }
}
