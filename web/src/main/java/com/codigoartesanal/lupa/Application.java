package com.codigoartesanal.lupa;

import com.codigoartesanal.lupa.config.MailConfig;
import com.codigoartesanal.lupa.config.WebConfig;
import com.codigoartesanal.lupa.config.infrastructure.InfrastructureConfig;
import com.codigoartesanal.lupa.config.PersistenceConfig;
import com.codigoartesanal.lupa.config.ServicesConfig;
import com.codigoartesanal.lupa.config.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "com.codigoartesanal.lupa.controller" })
@Import({ WebConfig.class, PersistenceConfig.class, ServicesConfig.class,
        SecurityConfig.class, InfrastructureConfig.class, MailConfig.class})
@EnableAutoConfiguration(exclude = {VelocityAutoConfiguration.class})
@EnableConfigurationProperties
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}