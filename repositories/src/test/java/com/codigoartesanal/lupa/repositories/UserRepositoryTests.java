package com.codigoartesanal.lupa.repositories;

import com.codigoartesanal.lupa.config.PersistenceConfig;
import com.codigoartesanal.lupa.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by betuzo on 25/01/15.
 */
@ActiveProfiles(value = "test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
@PropertySource("classpath:application.properties")
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testFindByNombreOficial() {
        String username = "jsoto";
        User user = userRepository.findByUsername(username);
        Assert.assertNotNull(user);
    }
}