package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStreamReader;
import java.io.Reader;

@Configuration
@ComponentScan("org.example")
public class AppConfig {

    @Bean
    public Reader systemInputReader() {
        return new InputStreamReader(System.in);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        var props = new PropertySourcesPlaceholderConfigurer();
        props.setLocation(new ClassPathResource("application.properties"));

        return props;
    }
}
