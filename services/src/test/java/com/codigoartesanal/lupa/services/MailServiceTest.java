package com.codigoartesanal.lupa.services;

import com.codigoartesanal.lupa.config.TestConfig;
import com.codigoartesanal.lupa.model.User;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by betuzo on 17/02/16.
 */
@ActiveProfiles(value = "test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Ignore
public class MailServiceTest {

    @Autowired
    MailService mailService;

    @Test
    public void testSend() {
        Map<String, Object> props = new HashMap<>();
        props.put("action", "Registrar");
        props.put("link", "lcalhosto:8090/#token/SDFSADF34FHF435YT67KJ45");
        User user = new User();
        user.setUsername("rolguin@grupobmv.com.mx");
        mailService.sendValidTokenUser(user, props);
    }
}
