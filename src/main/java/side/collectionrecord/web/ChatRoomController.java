package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import side.collectionrecord.domain.chatroom.ChatRoom;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.domain.userchatroom.UserChatRoom;
import side.collectionrecord.service.UserChatRoomService;

@RequiredArgsConstructor
@Controller
public class ChatRoomController {
    private final UserRepository userRepository;

    private final UserChatRoomService userChatRoomService;

    @GetMapping("/chatroom/{user1Id}/{user2Id}")
    public String chat(@PathVariable("user1Id") Long user1Id, @PathVariable("user2Id") Long user2Id, Model model) {

        UserChatRoom userChatRoom = userChatRoomService.createChatRoom(user1Id, user2Id);

        User user = userRepository.findById(user2Id).get();

        model.addAttribute("username", user.getUsername());

        model.addAttribute("chatRoomId", userChatRoom.getChatRoom().getId());

        return "/chatroom/chatroom";
    }
}
