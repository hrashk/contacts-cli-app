package org.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("init")
@PropertySource("classpath:application-init.properties")
public class InitConfig {
}
