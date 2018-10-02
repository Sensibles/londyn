package pl.arturkufa.londynmain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import pl.arturkufa.londynmain.model.WebSocketMessage;
import pl.arturkufa.londynmain.model.WebSocketResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class WebSocketServiceImpl  implements WebSocketService {

    @Value("${websocket.destination}")
    private String webSocketDestination;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    public WebSocketResponse send(@RequestBody WebSocketMessage webSocketMessage) {

        String message = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now())
                + " Message: " + webSocketMessage.getMessage();

        WebSocketResponse response = new WebSocketResponse(message);
        messagingTemplate.convertAndSend(webSocketDestination, response);

        try {
            System.out.println("send ws msg: " + objectMapper.writeValueAsString(response));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return response;
    }
}
