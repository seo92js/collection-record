package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import side.collectionrecord.domain.chatmessage.ChatMessage;
import side.collectionrecord.domain.chatmessage.ChatMessageRepository;
import side.collectionrecord.domain.chatroom.ChatRoom;
import side.collectionrecord.domain.chatroom.ChatRoomRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.domain.userchatroom.UserChatRoomRepository;
import side.collectionrecord.web.dto.ChatMessageAddDto;

@RequiredArgsConstructor
@Service
public class ChatMessageService {
    private final UserRepository userRepository;

    private final ChatRoomRepository chatRoomRepository;

    private final ChatMessageRepository chatMessageRepository;

    public Long addMessage(ChatMessageAddDto chatMessageAddDto){
        User user = userRepository.findById(chatMessageAddDto.getUserId()).get();
        ChatRoom chatRoom = chatRoomRepository.findById(chatMessageAddDto.getChatRoomId()).get();

        ChatMessage chatMessage = ChatMessage.builder()
                .user(user)
                .chatRoom(chatRoom)
                .message(chatMessageAddDto.getText())
                .build();

        return chatMessageRepository.save(chatMessage).getId();
    }
}
