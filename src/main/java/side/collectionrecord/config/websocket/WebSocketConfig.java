package side.collectionrecord.config.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import side.collectionrecord.handler.WebSocketHandlerByChat;
import side.collectionrecord.handler.WebSocketHandlerByNotification;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    private final WebSocketHandlerByChat webSocketHandlerByChat;

    private final WebSocketHandlerByNotification webSocketHandlerByNotification;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandlerByChat, "/chatroom").setAllowedOrigins("*");

        registry.addHandler(webSocketHandlerByNotification, "/notification").setAllowedOrigins("*");
    }
}
