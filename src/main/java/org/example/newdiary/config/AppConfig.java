package org.example.newdiary.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    @Value("${db.url}")
    private String url;
    @Value("${db.id}")
    private String id;
    @Value("${db.pw}")
    private String pw;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
                dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
                dataSource.setUrl(url);
                dataSource.setUsername(id);
                dataSource.setPassword(pw);
        return dataSource;
    }
}
