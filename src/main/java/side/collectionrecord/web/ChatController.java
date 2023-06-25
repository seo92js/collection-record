package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import side.collectionrecord.service.UserChatRoomService;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final UserChatRoomService userChatRoomService;

/*    @GetMapping("/chat/{user1Id}/{user2Id}")
    public String chat(@PathVariable("user1Id") Long user1Id, @PathVariable("user2Id") Long user2Id, Model model){

        userChatRoomService.createChatRoom(user1Id, user2Id);

        return "/chat/chat";
    }*/
}
