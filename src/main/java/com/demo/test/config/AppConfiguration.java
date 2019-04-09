package com.demo.test.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Configuration
@AllArgsConstructor
public class AppConfiguration {

    private final JdbcTemplate jdbcTemplate;

    @Bean
    public SimpleJdbcInsert simpleJdbcInsert(){
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.setTableName("Employee");
        simpleJdbcInsert.setGeneratedKeyName("empID");
        return simpleJdbcInsert;
    }

    @PostConstruct
    public void initDb(){
        Resource resource = new ClassPathResource("/db_scripts/init.sql");
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
        databasePopulator.execute(Objects.requireNonNull(jdbcTemplate.getDataSource()));
    }
}
