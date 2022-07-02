package com.weber.cs3230.Alexa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.weber.cs3230.HandlerSpeechlet")
public class AlexaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlexaServiceApplication.class, args);
    }

}