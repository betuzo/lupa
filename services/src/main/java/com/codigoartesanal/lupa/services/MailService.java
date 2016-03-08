package com.codigoartesanal.lupa.services;

import com.codigoartesanal.lupa.model.Persona;
import com.codigoartesanal.lupa.model.User;
import org.springframework.mail.SimpleMailMessage;

import java.util.Map;

/**
 * Created by betuzo on 25/02/16.
 */
public interface MailService {
    void sendValidTokenUser(User user, Map<String, Object> hTemplateVariables);
    void sendValidTokenIngresoByRole(String role, Map<String, Object> hTemplateVariables);
    void sendValidTokenIngresoToDonador(User donador, Map<String, Object> hTemplateVariables);
}
