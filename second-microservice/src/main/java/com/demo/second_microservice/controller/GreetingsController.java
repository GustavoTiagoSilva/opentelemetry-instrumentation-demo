package com.demo.second_microservice.controller;

import com.demo.second_microservice.producer.GreetingsProducer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetings")
public class GreetingsController {

    private final GreetingsProducer greetingsProducer;

    public GreetingsController(GreetingsProducer greetingsProducer) {
        this.greetingsProducer = greetingsProducer;
    }

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    public String hello() throws InterruptedException {
        Thread.sleep(10000);
        greetingsProducer.sendGreetings("Hello World");
        return "[second-microservice]: Hello World!";
    }

    @GetMapping("/goodbye")
    @ResponseStatus(HttpStatus.OK)
    public String goodbye() {
        greetingsProducer.sendGreetings("Goodbye");
        return "[second-microservice]: Goodbye!";
    }
}
