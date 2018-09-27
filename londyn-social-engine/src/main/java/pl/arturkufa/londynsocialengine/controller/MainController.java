package pl.arturkufa.londynsocialengine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/hello")
    public String testEndpoint(){
        return "Hello world!";
    }
}
