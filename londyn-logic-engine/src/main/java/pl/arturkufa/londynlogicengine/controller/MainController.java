package pl.arturkufa.londynlogicengine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/hello")
    public String testEndpoint(){
        return "Hello, I'm Londyn Logic Engine!";
    }
}
