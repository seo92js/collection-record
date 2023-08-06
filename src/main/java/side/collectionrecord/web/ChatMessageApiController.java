package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.service.ChatMessageService;

@RequiredArgsConstructor
@RestController
public class ChatMessageApiController {
    private final ChatMessageService chatMessageService;

    @PutMapping("/api/v1/chat-message/{chatRoomId}")
    public void updateChatMessage(@PathVariable Long chatRoomId, Model model){
        chatMessageService.updateChatMessage(chatRoomId, (Long) model.getAttribute("loginUserId"));
    }
}
