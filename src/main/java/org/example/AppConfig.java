package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.InputStreamReader;
import java.io.Reader;

@Configuration
@ComponentScan("org.example")
public class AppConfig {

    @Bean
    public Reader systemInputReader() {
        return new InputStreamReader(System.in);
    }
}
