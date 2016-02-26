package com.codigoartesanal.lupa.config.infrastructure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by betuzo on 11/05/15.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "com.codigoartesanal.lupa.infrastructure" })
public class InfrastructureConfig {


}
