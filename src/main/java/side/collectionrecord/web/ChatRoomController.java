package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.domain.userchatroom.UserChatRoom;
import side.collectionrecord.exception.UserNotFoundException;
import side.collectionrecord.service.ChatMessageService;
import side.collectionrecord.service.UserChatRoomService;
import side.collectionrecord.web.dto.GetChatMessageResponseDto;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatRoomController {
    private final UserRepository userRepository;

    private final ChatMessageService chatMessageService;

    private final UserChatRoomService userChatRoomService;

    @GetMapping("/chatroom/{user1Id}/{user2Id}")
    public String getChatRoom(@PathVariable("user1Id") Long user1Id, @PathVariable("user2Id") Long user2Id, Model model) {

        UserChatRoom userChatRoom = userChatRoomService.createUserChatRoom(user1Id, user2Id);

        User user = userRepository.findById(user2Id).orElseThrow(() -> new UserNotFoundException("유저가 없습니다."));

        model.addAttribute("receiverId", user2Id);

        model.addAttribute("username", user.getUsername());

        model.addAttribute("chatRoomId", userChatRoom.getChatRoom().getId());

        List<GetChatMessageResponseDto> chatRoomMessage = chatMessageService.getChatMessageByChatRoomId(userChatRoom.getChatRoom().getId());

        model.addAttribute("chatRoomMessages", chatRoomMessage);

        // 안 읽은 메세지 읽음으로 처리
        chatMessageService.updateChatMessage(userChatRoom.getChatRoom().getId(), user1Id);

        return "chatroom/chatroom";
    }
}
