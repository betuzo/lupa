package com.codigoartesanal.lupa.services;

import com.codigoartesanal.lupa.config.TestConfig;
import com.codigoartesanal.lupa.model.OrigenEstadistica;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by betuzo on 28/10/15.
 */
@ActiveProfiles(value = "test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class PathWebServiceTest {

    @Autowired
    PathWebService pathWeb;

    @Test
    public void testNullPathIngreso() {
        String path = pathWeb.getValidPathWebFoto(null, OriginPhoto.INGRESO);
        Assert.assertTrue(OriginPhoto.INGRESO.getPathDefault().equals(path));
    }

    @Test
    public void testNullPathPersona() {
        String path = pathWeb.getValidPathWebFoto(null, OriginPhoto.PERSONA);
        Assert.assertTrue(OriginPhoto.PERSONA.getPathDefault().equals(path));
    }

    @Test
    public void testEmptyPathIngreso() {
        String path = pathWeb.getValidPathWebFoto("", OriginPhoto.INGRESO);
        Assert.assertTrue(OriginPhoto.INGRESO.getPathDefault().equals(path));
    }

    @Test
    public void testEmptyPathPersona() {
        String path = pathWeb.getValidPathWebFoto("", OriginPhoto.PERSONA);
        Assert.assertTrue(OriginPhoto.PERSONA.getPathDefault().equals(path));
    }

    @Test
    public void testInvalidPathIngreso() {
        String path = pathWeb.getValidPathWebFoto("asdas", OriginPhoto.INGRESO);
        Assert.assertTrue(OriginPhoto.INGRESO.getPathDefault().equals(path));
    }

    @Test
    public void testInvalidPathPersona() {
        String path = pathWeb.getValidPathWebFoto("asdas", OriginPhoto.PERSONA);
        Assert.assertTrue(OriginPhoto.PERSONA.getPathDefault().equals(path));
    }

    @Test
    public void testValidPathPersona() {
        String name = "persona-test.png";
        String path = pathWeb.getValidPathWebFoto(name, OriginPhoto.PERSONA);
        Assert.assertTrue(path.contains(name));
    }

    @Test
    public void testValidPathIngreso() {
        String name = "ingreso-test.png";
        String path = pathWeb.getValidPathWebFoto(name, OriginPhoto.INGRESO);
        Assert.assertTrue(path.contains(name));
    }

}
