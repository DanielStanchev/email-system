package com.tinqinacademy.email.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@Slf4j
@ComponentScan(basePackages = "com.tinqinacademy.email")
//@EntityScan(basePackages = "com.tinqinacademy.email.persistence.entity")
//@EnableJpaRepositories(basePackages = "com.tinqinacademy.email.persistence.repository")
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class Application {
    public static void main(String[] args) {SpringApplication.run(Application.class, args);}
}
