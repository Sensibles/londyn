package pl.arturkufa.londynlogicengine.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.arturkufa.londyncommon.kafka.KafkaProducer;
import pl.arturkufa.londyncommon.kafka.model.TestMessage;

@RestController
public class MainController {

    @Autowired
    private KafkaProducer<TestMessage> testMessageKafkaProducer;

    @GetMapping("/hello")
    public String testEndpoint(){
        return "Hello, I'm Londyn Logic Engine!";
    }

    @PostMapping(value = "/sendKafkaMessage", consumes = "application/json", produces = "application/json")
    public TestMessage sendKafkaTest(@RequestBody TestMessage testMessage){
        try {
            testMessageKafkaProducer.send(testMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return testMessage;
    }

}
