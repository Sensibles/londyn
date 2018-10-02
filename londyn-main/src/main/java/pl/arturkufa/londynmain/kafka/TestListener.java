package pl.arturkufa.londynmain.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import pl.arturkufa.londynmain.model.WebSocketMessage;
import pl.arturkufa.londynmain.service.WebSocketService;

import java.util.List;

@Component
public class TestListener {

    @Autowired
    private WebSocketService webSocketService;

    @KafkaListener(topics = "${kafka.topic1}")
    public void processMessage(String message,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) List<String> topics,
                               @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        WebSocketMessage webSocketMessage = new WebSocketMessage(message);
        webSocketService.send(webSocketMessage);
        System.out.printf("%s-%d[%d] \"%s\"\n", topics.get(0), partitions.get(0), offsets.get(0), message);
    }
}
