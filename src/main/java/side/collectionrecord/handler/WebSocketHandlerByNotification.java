package side.collectionrecord.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.exception.UserNotFoundException;
import side.collectionrecord.service.NotificationService;
import side.collectionrecord.web.dto.CreateNotificationRequestDto;
import side.collectionrecord.web.dto.GetNotificationResponseDto;

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
        ObjectMapper objectMapper = new ObjectMapper();

        String payload = message.getPayload();

        if (addSession(payload, session))
            return;

        // 일반 알림
        CreateNotificationRequestDto createNotificationRequestDto = objectMapper.readValue(payload, CreateNotificationRequestDto.class);
        Long id = notificationService.createNotification(createNotificationRequestDto);

        // 상대한테 send
        WebSocketSession sessionByUsername = findSessionByUsername(createNotificationRequestDto.getReceiverName());
        sendToClient(sessionByUsername);
    }

    // 클라이언트 연결 성립 시 처리
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //username 저장을 위해 handleTextMessage 에서 처리
    }

    // 클라이언트 연결 종료 시 처리
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 연결이 종료된 세션에 매핑된 유저네임을 찾아서 제거
        String usernameToRemove = findSessionKeyByValue(session);

        if (usernameToRemove != null)
            webSocketSessions.remove(usernameToRemove);
    }

    private boolean addSession(String payload, WebSocketSession session) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode messageNode = objectMapper.readTree(payload);

        // 만약 메시지의 타입이 "username"이면, 세션에 username 저장하고 리턴
        if (messageNode.has("type") && messageNode.get("type").asText().equals("username")) {
            String username = messageNode.get("value").asText();
            session.getAttributes().put("username", username);

            if (!webSocketSessions.containsKey(username))
                webSocketSessions.put(username, session);

            User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("유저가 없습니다."));

            List<GetNotificationResponseDto> notConfirmNotification = notificationService.getAllNotificationByUserIdConfirmFalse(user.getId());

            // 안읽은 알림이 있으면 본인에게 send
            if (notConfirmNotification.size() > 0)
                sendToClient(session);

            return true;
        }

        return false;
    }

    private WebSocketSession findSessionByUsername(String username){
        WebSocketSession webSocketSession = webSocketSessions.get(username);

        return webSocketSession;
    }
    private String findSessionKeyByValue(WebSocketSession session){
        String usernameToRemove = null;

        for (Map.Entry<String, WebSocketSession> entry : webSocketSessions.entrySet()) {
            if (entry.getValue().equals(session)) {
                usernameToRemove = entry.getKey();
                break;
            }
        }

        return usernameToRemove;
    }

    private void sendToClient(WebSocketSession session) throws IOException {
        if (session != null)
            session.sendMessage(new TextMessage("send"));
    }

}
