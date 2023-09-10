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
import side.collectionrecord.web.dto.CreateChatMessageRequestDto;
import side.collectionrecord.web.dto.GetChatMessageResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatMessageService {
    private final UserRepository userRepository;

    private final ChatRoomRepository chatRoomRepository;

    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public GetChatMessageResponseDto getChatMessageById(Long id){
        ChatMessage chatMessage = chatMessageRepository.findById(id).get();

        GetChatMessageResponseDto getChatMessageResponseDto = new GetChatMessageResponseDto(chatMessage);

        return getChatMessageResponseDto;
    }

    @Transactional
    public Long createChatMessage(CreateChatMessageRequestDto createChatMessageRequestDto){
        User sender = userRepository.findById(createChatMessageRequestDto.getSenderId()).get();
        User receiver = userRepository.findById(createChatMessageRequestDto.getReceiverId()).get();

        ChatRoom chatRoom = chatRoomRepository.findById(createChatMessageRequestDto.getChatRoomId()).get();

        ChatMessage chatMessage = ChatMessage.builder()
                .sender(sender)
                .receiver(receiver)
                .chatRoom(chatRoom)
                .message(createChatMessageRequestDto.getMessage())
                .confirm(false)
                .build();

        chatRoom.preUpdate();

        return chatMessageRepository.save(chatMessage).getId();
    }

    @Transactional
    public List<GetChatMessageResponseDto> getChatMessageByChatRoomId(Long chatRoomId){

        return chatMessageRepository.findByChatRoomId(chatRoomId).stream()
                .map(GetChatMessageResponseDto::new)
                .collect(Collectors.toList());

    }

    @Transactional
    public void updateChatMessage(Long chatRoomId, Long userId){
        List<ChatMessage> confirmFalseChatMessage = chatMessageRepository.findByChatroomIdAndUserIdConfirmFalse(chatRoomId, userId);

        for (ChatMessage chatMessage : confirmFalseChatMessage)
            chatMessage.setConfirm();
    }
}
