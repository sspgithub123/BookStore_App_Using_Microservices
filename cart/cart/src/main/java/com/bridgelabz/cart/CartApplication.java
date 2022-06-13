package com.bridgelabz.cart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class CartApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
        System.out.println("Welcome to the book store app");
        log.info("cart started");
    }

    /** Setting rest template. **/
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}
