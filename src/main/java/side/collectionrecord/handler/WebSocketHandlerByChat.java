package side.collectionrecord.handler;

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


@RequiredArgsConstructor
@Component
public class WebSocketHandlerByChat extends TextWebSocketHandler {

    private final ChatMessageService chatMessageService;

    private static HashMap<String, WebSocketSession> webSocketSessions = new HashMap<String, WebSocketSession>();

    // 클라이언트로부터 수신된 메세지 처리
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();

        ObjectMapper objectMapper = new ObjectMapper();

        ChatMessageAddRequestDto chatMessageAddRequestDto = objectMapper.readValue(payload, ChatMessageAddRequestDto.class);

        Long id = chatMessageService.addMessage(chatMessageAddRequestDto);

        ChatMessageResponseDto chatMessageResponseDto = chatMessageService.findById(id);

        sendToClient(session, chatMessageResponseDto);
    }

    // 클라이언트 연결 성립 시 처리
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessions.add(session);
    }

    // 클라이언트 연결 종료 시 처리
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessions.remove(session);
    }

    private void sendToClient(WebSocketSession session, ChatMessageResponseDto chatMessageResponseDto) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(chatMessageResponseDto);

        session.sendMessage(new TextMessage(json));
    }
}
