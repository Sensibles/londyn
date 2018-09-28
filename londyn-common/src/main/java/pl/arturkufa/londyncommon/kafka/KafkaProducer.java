package pl.arturkufa.londyncommon.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer<T> {
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    //todo temporary solution with this topic here

    public void send(String topic, T message) throws JsonProcessingException {
        String JSONMessage = objectMapper.writeValueAsString(message);
        kafkaTemplate.send(topic, JSONMessage);
        System.out.println("Sent sample message [" + message + "] to " + topic);
    }
}
