package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.chatmessage.ChatMessage;
import side.collectionrecord.domain.chatmessage.ChatMessageRepository;
import side.collectionrecord.domain.chatroom.ChatRoom;
import side.collectionrecord.domain.chatroom.ChatRoomRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.ChatMessageAddRequestDto;
import side.collectionrecord.web.dto.ChatMessageResponseDto;

@RequiredArgsConstructor
@Service
public class ChatMessageService {
    private final UserRepository userRepository;

    private final ChatRoomRepository chatRoomRepository;

    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public ChatMessageResponseDto findById(Long id){
        ChatMessage chatMessage = chatMessageRepository.findById(id).get();

        ChatMessageResponseDto chatMessageResponseDto = new ChatMessageResponseDto(chatMessage);

        return chatMessageResponseDto;
    }

    @Transactional
    public Long addMessage(ChatMessageAddRequestDto chatMessageAddRequestDto){
        User user = userRepository.findById(chatMessageAddRequestDto.getUserId()).get();
        ChatRoom chatRoom = chatRoomRepository.findById(chatMessageAddRequestDto.getChatRoomId()).get();

        ChatMessage chatMessage = ChatMessage.builder()
                .user(user)
                .chatRoom(chatRoom)
                .message(chatMessageAddRequestDto.getText())
                .build();

        return chatMessageRepository.save(chatMessage).getId();
    }
}
