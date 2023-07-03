package side.collectionrecord.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import side.collectionrecord.service.NotificationService;
import side.collectionrecord.web.dto.NotificationAddRequestDto;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class WebSocketHandlerByNotification extends TextWebSocketHandler {

    private final NotificationService notificationService;

    private static List<WebSocketSession> webSocketSessions = new ArrayList<>();

    // 클라이언트로부터 수신된 메세지 처리
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();

        ObjectMapper objectMapper = new ObjectMapper();

        NotificationAddRequestDto notificationAddRequestDto = objectMapper.readValue(payload, NotificationAddRequestDto.class);

        notificationService.save(notificationAddRequestDto);
    }

    // 클라이언트 연결 성립 시 처리
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        webSocketSessions.add(session);

        //알림 조회
    }

    // 클라이언트 연결 종료 시 처리
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        webSocketSessions.remove(session);
    }
}
