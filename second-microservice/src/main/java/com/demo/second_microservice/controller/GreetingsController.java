package com.demo.second_microservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
public class GreetingsController {

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    public String hello() {
        return "[second-microservice]: Hello World!";
    }

    @GetMapping("/goodbye")
    @ResponseStatus(HttpStatus.OK)
    public String goodbye() {
        return "[second-microservice]: Goodbye!";
    }
}
