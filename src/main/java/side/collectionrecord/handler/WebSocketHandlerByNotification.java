package side.collectionrecord.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import side.collectionrecord.domain.notification.Notification;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.service.NotificationService;
import side.collectionrecord.web.dto.NotificationAddRequestDto;
import side.collectionrecord.web.dto.NotificationResponseDto;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class WebSocketHandlerByNotification extends TextWebSocketHandler {
    private final UserRepository userRepository;

    private final NotificationService notificationService;

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

            if (!webSocketSessions.containsKey(username)){
                webSocketSessions.put(username, session);
            }

            User user = userRepository.findByUsername(username).get();

            List<NotificationResponseDto> notReadNotification = notificationService.findNotReadNotification(user.getId());

            //본인한테
            if (notReadNotification.size() > 0){
                sendToClient(session);
            }

            return;
        }

        // 일반 알림
        NotificationAddRequestDto notificationAddRequestDto = objectMapper.readValue(payload, NotificationAddRequestDto.class);

        Long id = notificationService.save(notificationAddRequestDto);

        //상대한테 send 해야 함
    }

    // 클라이언트 연결 성립 시 처리
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //username 저장을 위해 handleTextMessage 에서 처리
        //webSocketSessions.add(session);
        return;
    }

    // 클라이언트 연결 종료 시 처리
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 연결이 종료된 세션에 매핑된 유저네임을 찾아서 제거
        String usernameToRemove = null;

        for (Map.Entry<String, WebSocketSession> entry : webSocketSessions.entrySet()) {
            if (entry.getValue().equals(session)) {
                usernameToRemove = entry.getKey();
                break;
            }
        }
        if (usernameToRemove != null) {
            webSocketSessions.remove(usernameToRemove);
        }
    }

    private void sendToClient(WebSocketSession session) throws IOException {
        session.sendMessage(new TextMessage("send"));
    }
}
