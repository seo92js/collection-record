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
import side.collectionrecord.exception.ChatMessageNotFoundException;
import side.collectionrecord.exception.ChatRoomNotFoundException;
import side.collectionrecord.exception.UserNotFoundException;
import side.collectionrecord.web.dto.ChatMessageRequestDto;
import side.collectionrecord.web.dto.ChatMessageResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatMessageService {
    private final UserRepository userRepository;

    private final ChatRoomRepository chatRoomRepository;

    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public ChatMessageResponseDto getChatMessageById(Long id){
        ChatMessage chatMessage = chatMessageRepository.findById(id).orElseThrow(() -> new ChatMessageNotFoundException("메세지가 없습니다."));

        ChatMessageResponseDto chatMessageResponseDto = new ChatMessageResponseDto(chatMessage);

        return chatMessageResponseDto;
    }

    @Transactional
    public Long createChatMessage(ChatMessageRequestDto chatMessageRequestDto){
        User sender = userRepository.findById(chatMessageRequestDto.getSenderId()).orElseThrow(() -> new UserNotFoundException("유저가 없습니다."));
        User receiver = userRepository.findById(chatMessageRequestDto.getReceiverId()).orElseThrow(() -> new UserNotFoundException("유저가 없습니다."));
        ChatRoom chatRoom = chatRoomRepository.findById(chatMessageRequestDto.getChatRoomId()).orElseThrow(() -> new ChatRoomNotFoundException("채팅방이 없습니다."));

        ChatMessage chatMessage = ChatMessage.builder()
                .sender(sender)
                .receiver(receiver)
                .chatRoom(chatRoom)
                .message(chatMessageRequestDto.getMessage())
                .confirm(false)
                .build();

        chatRoom.preUpdate();

        return chatMessageRepository.save(chatMessage).getId();
    }

    @Transactional
    public List<ChatMessageResponseDto> getChatMessageByChatRoomId(Long chatRoomId){

        return chatMessageRepository.findByChatRoomId(chatRoomId).stream()
                .map(ChatMessageResponseDto::new)
                .collect(Collectors.toList());

    }

    @Transactional
    public void updateChatMessage(Long chatRoomId, Long userId){
        List<ChatMessage> confirmFalseChatMessage = chatMessageRepository.findByChatroomIdAndUserIdConfirmFalse(chatRoomId, userId);

        for (ChatMessage chatMessage : confirmFalseChatMessage)
            chatMessage.setConfirm();
    }
}
