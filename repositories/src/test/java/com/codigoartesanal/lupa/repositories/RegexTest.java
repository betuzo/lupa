package com.codigoartesanal.lupa.repositories;

import com.codigoartesanal.lupa.config.PersistenceConfig;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by betuzo on 2/06/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class})
@PropertySource("classpath:application.properties")
@ActiveProfiles(value = "test")
public class RegexTest {

    @Test
    public void testRegex() {
        String regex = "^(?=[A-Za-z0-9])(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])([\\w\\\\Q!¡@#$%&*()_+\\-={}\\[\\]\\/:;\"'<>,.?¿\\\\E]){8,16}$";

        String password= "Maas1d#sdgs";

        Assert.assertTrue(password.matches(regex));
    }
}
