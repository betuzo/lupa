package com.codigoartesanal.lupa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by betuzo on 16/02/16.
 */
@Configuration
@Import({ PersistenceConfig.class, ServicesConfig.class, MailConfig.class})
public class TestConfig {
}
