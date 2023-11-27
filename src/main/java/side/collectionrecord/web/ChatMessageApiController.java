package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.config.auth.dto.SessionUser;
import side.collectionrecord.service.ChatMessageService;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class ChatMessageApiController {
    private final ChatMessageService chatMessageService;

    @PutMapping("/api/v1/chat-message/{chatRoomId}")
    public void updateChatMessage(@PathVariable Long chatRoomId, HttpSession httpSession){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        chatMessageService.updateChatMessage(chatRoomId, user.getId());
    }
}
