package com.bridgelabz.bookstoreeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BookStoreEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookStoreEurekaServerApplication.class, args);
        System.out.println("Welcome to the book store app");
    }
}
