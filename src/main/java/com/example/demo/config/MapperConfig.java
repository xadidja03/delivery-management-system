package com.example.demo.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public EntityMapper foodMapper() {
        return Mappers.getMapper(EntityMapper.class);
    }
}

