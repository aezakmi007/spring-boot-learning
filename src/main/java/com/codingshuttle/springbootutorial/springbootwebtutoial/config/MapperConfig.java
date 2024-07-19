package com.codingshuttle.springbootutorial.springbootwebtutoial.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean

    public ModelMapper getModelMapper(){
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }
}
