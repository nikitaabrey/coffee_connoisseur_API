package com.coffeecon.app.config;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.main")
    public HikariDataSource hikariDataSource() {
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(HikariDataSource source) {
        return new JdbcTemplate(source);
    }


    @Bean
    NamedParameterJdbcTemplate namedParameterJdbcTemplate(HikariDataSource source) {
        return new NamedParameterJdbcTemplate(source);
    }

    @Bean
    public DataSourceTransactionManager txnManager(HikariDataSource source) {
        return new DataSourceTransactionManager(source);
    }

}
