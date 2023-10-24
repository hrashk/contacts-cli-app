package io.github.hrashk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.InputStreamReader;
import java.io.Reader;

@Configuration
@ComponentScan("io.github.hrashk")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public Reader systemInputReader() {
        return new InputStreamReader(System.in);
    }
}
