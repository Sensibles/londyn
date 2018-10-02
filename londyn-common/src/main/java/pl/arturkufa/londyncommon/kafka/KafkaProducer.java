package pl.arturkufa.londyncommon.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

public class KafkaProducer<T> {
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public KafkaProducer(String topic) {
        this.topic = topic;
    }

    public void send(T message) throws JsonProcessingException {
        String JSONMessage = objectMapper.writeValueAsString(message);
        kafkaTemplate.send(topic, JSONMessage);
        System.out.println("Sent sample message [" + message + "] to " + topic);
    }
}
