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
    public void testNullPathEquipoDefault() {
        String path = pathWeb.getValidPathWebLogo(null, null);
        Assert.assertTrue(PathPhoto.EQUIPO_DEFAULT.getPath().equals(path));
    }

    @Test
    public void testNullPathEquipoLocal() {
        String path = pathWeb.getValidPathWebLogo(null, OrigenEstadistica.LOCAL);
        Assert.assertTrue(PathPhoto.EQUIPO_DEFAULT_LOCAL.getPath().equals(path));
    }

    @Test
    public void testNullPathEquipoVisita() {
        String path = pathWeb.getValidPathWebLogo(null, OrigenEstadistica.VISITA);
        Assert.assertTrue(PathPhoto.EQUIPO_DEFAULT_VISITA.getPath().equals(path));
    }

    @Test
    public void testNullPathArbitro() {
        String path = pathWeb.getValidPathWebFoto(null, OriginPhoto.ARBITRO);
        Assert.assertTrue(PathPhoto.ARBITRO_DEFAULT.getPath().equals(path));
    }

    @Test
    public void testNullPathJugador() {
        String path = pathWeb.getValidPathWebFoto(null, OriginPhoto.PERSONA);
        Assert.assertTrue(PathPhoto.JUGADOR_DEFAULT.getPath().equals(path));
    }

    @Test
    public void testEmptyPathEquipoDefault() {
        String path = pathWeb.getValidPathWebLogo("", null);
        Assert.assertTrue(PathPhoto.EQUIPO_DEFAULT.getPath().equals(path));
    }

    @Test
    public void testEmptyPathEquipoLocal() {
        String path = pathWeb.getValidPathWebLogo("", OrigenEstadistica.LOCAL);
        Assert.assertTrue(PathPhoto.EQUIPO_DEFAULT_LOCAL.getPath().equals(path));
    }

    @Test
    public void testEmptyPathEquipoVisita() {
        String path = pathWeb.getValidPathWebLogo("", OrigenEstadistica.VISITA);
        Assert.assertTrue(PathPhoto.EQUIPO_DEFAULT_VISITA.getPath().equals(path));
    }

    @Test
    public void testEmptyPathArbitro() {
        String path = pathWeb.getValidPathWebFoto("", OriginPhoto.ARBITRO);
        Assert.assertTrue(PathPhoto.ARBITRO_DEFAULT.getPath().equals(path));
    }

    @Test
    public void testEmptyPathJugador() {
        String path = pathWeb.getValidPathWebFoto("", OriginPhoto.PERSONA);
        Assert.assertTrue(PathPhoto.JUGADOR_DEFAULT.getPath().equals(path));
    }

    @Test
    public void testInvalidPathEquipoDefault() {
        String path = pathWeb.getValidPathWebLogo("sadasd", null);
        Assert.assertTrue(PathPhoto.EQUIPO_DEFAULT.getPath().equals(path));
    }

    @Test
    public void testInvalidPathEquipoLocal() {
        String path = pathWeb.getValidPathWebLogo("sadasd", OrigenEstadistica.LOCAL);
        Assert.assertTrue(PathPhoto.EQUIPO_DEFAULT_LOCAL.getPath().equals(path));
    }

    @Test
    public void testInvalidPathEquipoVisita() {
        String path = pathWeb.getValidPathWebLogo("sadasd", OrigenEstadistica.VISITA);
        Assert.assertTrue(PathPhoto.EQUIPO_DEFAULT_VISITA.getPath().equals(path));
    }

    @Test
    public void testInvalidPathArbitro() {
        String path = pathWeb.getValidPathWebFoto("asdas", OriginPhoto.ARBITRO);
        Assert.assertTrue(PathPhoto.ARBITRO_DEFAULT.getPath().equals(path));
    }

    @Test
    public void testInvalidPathJugador() {
        String path = pathWeb.getValidPathWebFoto("asdas", OriginPhoto.PERSONA);
        Assert.assertTrue(PathPhoto.JUGADOR_DEFAULT.getPath().equals(path));
    }

    @Test
    public void testValidPathEquipoDefault() {
        String name = "equipo-test.svg";
        String path = pathWeb.getValidPathWebLogo(name, null);
        Assert.assertTrue(path.contains(name));
    }

    @Test
    public void testValidPathEquipoLocal() {
        String name = "equipo-test.svg";
        String path = pathWeb.getValidPathWebLogo(name, OrigenEstadistica.LOCAL);
        Assert.assertTrue(path.contains(name));
    }

    @Test
    public void testValidPathEquipoVisita() {
        String name = "equipo-test.svg";
        String path = pathWeb.getValidPathWebLogo(name, OrigenEstadistica.VISITA);
        Assert.assertTrue(path.contains(name));
    }

    @Test
    public void testValidPathJugador() {
        String name = "jugador-test.png";
        String path = pathWeb.getValidPathWebFoto(name, OriginPhoto.PERSONA);
        Assert.assertTrue(path.contains(name));
    }

    @Test
    public void testValidPathArbitro() {
        String name = "arbitro-test.png";
        String path = pathWeb.getValidPathWebFoto(name, OriginPhoto.ARBITRO);
        Assert.assertTrue(path.contains(name));
    }

}
