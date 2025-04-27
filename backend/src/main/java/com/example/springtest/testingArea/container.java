package com.example.springtest.testingArea;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class container {
    @Bean
    public tet Tet() {
        return new tet();
    }
}
