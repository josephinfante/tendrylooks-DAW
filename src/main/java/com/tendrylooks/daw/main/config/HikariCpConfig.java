package com.tendrylooks.daw.main.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HikariCpConfig {

    @Value("${DB_TRENDYLOOKS_URL}")
    private String dbTrendyLooksUrl;
    @Value("${DB_TRENDYLOOKS_USER}")
    private String dbTrendyLooksUser;
    @Value("${DB_TRENDYLOOKS_PASS}")
    private String dbTrendyLooksPass;
    @Value("${DB_TRENDYLOOKS_DRIVER}")
    private String dbTrendyLooksDriver;

    @Bean
    public HikariDataSource hikariDataSource() {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(dbTrendyLooksUrl);
        config.setUsername(dbTrendyLooksUser);
        config.setPassword("");
        config.setDriverClassName(dbTrendyLooksDriver);

        config.setMaximumPoolSize(20);
        config.setMinimumIdle(5);
        config.setIdleTimeout(300000);
        config.setConnectionTimeout(30000);

        System.out.println("###### HikariCP initialized ######");
        return new HikariDataSource(config);

    }

}
