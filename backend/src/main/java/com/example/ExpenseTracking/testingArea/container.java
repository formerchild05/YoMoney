package com.example.ExpenseTracking.testingArea;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class container {
    @Bean
    public tet Tet() {
        return new tet();
    }
}
