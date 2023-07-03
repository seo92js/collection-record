package side.collectionrecord.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import side.collectionrecord.service.ChatMessageService;
import side.collectionrecord.service.NotificationService;
import side.collectionrecord.web.dto.ChatMessageAddRequestDto;
import side.collectionrecord.web.dto.ChatMessageResponseDto;
import side.collectionrecord.web.dto.NotificationAddRequestDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@Component
public class WebSocketHandlerByChat extends TextWebSocketHandler {

    private final ChatMessageService chatMessageService;

    private final Map<String, WebSocketSession> webSocketSessions = new HashMap<>();

    // 클라이언트로부터 수신된 메세지 처리
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode messageNode = objectMapper.readTree(payload);

        // 만약 메시지의 타입이 "username"이면, 세션에 username 저장하고 리턴
        if (messageNode.has("type") && messageNode.get("type").asText().equals("username")) {
            String username = messageNode.get("value").asText();
            session.getAttributes().put("username", username);
            webSocketSessions.put(username, session);
            return;
        }

        // 일반 메세지
        ChatMessageAddRequestDto chatMessageAddRequestDto = objectMapper.readValue(payload, ChatMessageAddRequestDto.class);

        Long id = chatMessageService.addMessage(chatMessageAddRequestDto);

        ChatMessageResponseDto chatMessageResponseDto = chatMessageService.findById(id);

        sendToClient(chatMessageResponseDto);
    }

    // 클라이언트 연결 성립 시 처리
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //username 저장을 위해 handleTextMessage 에서 처리
        //webSocketSessions.add(session);
    }

    // 클라이언트 연결 종료 시 처리
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessions.remove(session);
    }

    private void sendToClient(ChatMessageResponseDto chatMessageResponseDto) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(chatMessageResponseDto);

        WebSocketSession webSocketSession = webSocketSessions.get(chatMessageResponseDto.getReceiverName());

        webSocketSession.sendMessage(new TextMessage(json));
    }
}
