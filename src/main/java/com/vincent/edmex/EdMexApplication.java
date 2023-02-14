package com.vincent.edmex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class EdMexApplication {

    public static void main(String[] args) {
        //TODO: da rivedere
        System.setProperty("org.jooq.no-tips", "true");
        System.setProperty("org.jooq.no-logo", "true");
        SpringApplication.run(EdMexApplication.class, args);

    }

}
