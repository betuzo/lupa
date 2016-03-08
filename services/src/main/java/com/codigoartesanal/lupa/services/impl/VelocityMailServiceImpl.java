package com.codigoartesanal.lupa.services.impl;

import com.codigoartesanal.lupa.model.User;
import com.codigoartesanal.lupa.model.UserRole;
import com.codigoartesanal.lupa.repositories.UserRoleRepository;
import com.codigoartesanal.lupa.services.MailService;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by betuzo on 15/02/16.
 */
@Service
public class VelocityMailServiceImpl implements MailService {

    @Autowired
    VelocityEngine velocityEngine;

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    SimpleMailMessage templateMessage;

    @Override
    public void sendValidTokenUser(User user, Map<String, Object> hTemplateVariables) {
        templateMessage.setTo(user.getUsername());
        templateMessage.setCc(user.getUsername());

        send(templateMessage, hTemplateVariables, "/emailBody.vm");
    }

    @Override
    public void sendValidTokenIngresoByRole(String role, Map<String, Object> hTemplateVariables) {
        Iterator<UserRole> itRoles = userRoleRepository.findAllByRole(role).iterator();
        while (itRoles.hasNext()) {
            UserRole userRole = itRoles.next();
            templateMessage.setTo(userRole.getUser().getUsername());
            templateMessage.setCc(userRole.getUser().getUsername());

            send(templateMessage, hTemplateVariables, "/emailValidaIngresoBody.vm");
        }
    }

    @Override
    public void sendValidTokenIngresoToDonador(User donador, Map<String, Object> hTemplateVariables) {
        templateMessage.setTo(donador.getUsername());
        templateMessage.setCc(donador.getUsername());

        send(templateMessage, hTemplateVariables, "/emailValidaIngresoBody.vm");
    }

    private void send(final SimpleMailMessage msg, final Map<String, Object> hTemplateVariables, String templateLocation) {
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(msg.getTo());
            message.setCc(msg.getCc());
            message.setFrom(msg.getFrom());
            message.setSubject(msg.getSubject());

            String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templateLocation, "UTF-8", hTemplateVariables);

            message.setText(body, true);
        };

        mailSender.send(preparator);
    }
}