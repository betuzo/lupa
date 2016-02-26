package com.codigoartesanal.lupa.repositories;

import com.codigoartesanal.lupa.config.PersistenceConfig;
import com.codigoartesanal.lupa.model.User;
import com.codigoartesanal.lupa.model.UserRole;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

/**
 * Created by betuzo on 25/01/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
@PropertySource("classpath:application.properties")
@ActiveProfiles(value = "test")
public class UserRoleRepositoryTests {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Test
    public void testFindByNombreOficial() {
        String username = "jsoto";
        User user = new User();
        user.setUsername(username);
        Set<UserRole> userRole = userRoleRepository.findAllByUser(user);
        Assert.assertNotNull(userRole);
    }
}