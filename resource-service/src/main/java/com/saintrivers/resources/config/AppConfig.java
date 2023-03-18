package com.saintrivers.resources.config;

import com.saintrivers.common.mapper.PaginationMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public PaginationMapper paginationMapper(){
        return new PaginationMapper();
    }
}
