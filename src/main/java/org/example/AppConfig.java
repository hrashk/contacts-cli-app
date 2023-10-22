package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.InputStreamReader;

@Configuration
@ComponentScan("org.example")
public class AppConfig {

    @Bean
    public UiParser parser() {
        return new UiParser(new InputStreamReader(System.in));
    }
}
