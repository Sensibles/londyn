package pl.arturkufa.londynmain.service;

import org.springframework.web.bind.annotation.RequestBody;
import pl.arturkufa.londynmain.model.WebSocketMessage;
import pl.arturkufa.londynmain.model.WebSocketResponse;

public interface WebSocketService {

    WebSocketResponse send(@RequestBody WebSocketMessage webSocketMessage);
}
