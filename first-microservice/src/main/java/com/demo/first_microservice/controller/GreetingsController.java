package com.demo.first_microservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/greetings")
public class GreetingsController {

    private final RestClient restClient = RestClient.create();

    private final String secondMicroserviceUri;

    public GreetingsController(@Value("${second-microservice.uri}") String secondMicroserviceUri) {
        this.secondMicroserviceUri = secondMicroserviceUri;
    }

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    public String hello() {
        return "[first-microservice]: Hello World!";
    }

    @GetMapping("/goodbye")
    @ResponseStatus(HttpStatus.OK)
    public String goodbye() {
        return "[first-microservice]: Goodbye!";
    }

    @GetMapping("/hello-second-microservice")
    public String helloSecondMicroservice() {
        return restClient.get().uri(secondMicroserviceUri.concat("/greetings/hello")).retrieve().body(String.class);
    }

    @GetMapping("/goodbye-second-microservice")
    public String goodbyeSecondMicroservice() {
        return restClient.get().uri(secondMicroserviceUri.concat("/greetings/goodbye")).retrieve().body(String.class);
    }
}
