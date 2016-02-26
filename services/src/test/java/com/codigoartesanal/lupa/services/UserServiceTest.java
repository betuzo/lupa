package com.codigoartesanal.lupa.services;

import com.codigoartesanal.lupa.config.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by betuzo on 8/05/15.
 */
@ActiveProfiles(value = "test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testFindByUsername() {
        Map<String, Object> userMap = userService.findByUsername("jsoto");
        Assert.assertNotNull(userMap);
    }
}
