package com.codigoartesanal.lupa.services.impl;

import com.codigoartesanal.lupa.model.User;
import com.codigoartesanal.lupa.services.MailService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by betuzo on 14/04/16.
 */
@Service
@Profile({"localbmvstorage", "localhomestorage", "test"})
public class MockMailServiceImpl implements MailService {
    @Override
    public void sendValidTokenUser(User user, Map<String, Object> hTemplateVariables) {

    }

    @Override
    public void sendValidTokenIngresoByRole(String role, Map<String, Object> hTemplateVariables) {

    }

    @Override
    public void sendValidTokenIngresoToDonador(User donador, Map<String, Object> hTemplateVariables) {

    }
}
