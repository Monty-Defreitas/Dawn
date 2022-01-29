package com.leonde.seconddawn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages ={"com.leonde"} )
public class SecondDawnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondDawnApplication.class, args);
    }

}
