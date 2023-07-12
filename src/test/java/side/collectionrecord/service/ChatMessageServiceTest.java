package side.collectionrecord.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import side.collectionrecord.domain.chatmessage.ChatMessage;
import side.collectionrecord.domain.chatmessage.ChatMessageRepository;
import side.collectionrecord.domain.chatroom.ChatRoom;
import side.collectionrecord.domain.chatroom.ChatRoomRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.domain.user.UserRole;
import side.collectionrecord.web.dto.ChatMessageAddRequestDto;
import side.collectionrecord.web.dto.ChatMessageResponseDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChatMessageServiceTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    ChatMessageRepository chatMessageRepository;

    @Autowired
    ChatMessageService chatMessageService;

    @Test
    public void 아이디로_찾기() {
        User sender = User.builder()
                .username("user")
                .userRole(UserRole.USER)
                .password("1")
                .profileImage(null)
                .build();

        User receiver = User.builder()
                .username("user")
                .userRole(UserRole.USER)
                .password("1")
                .profileImage(null)
                .build();

        userRepository.save(sender);
        userRepository.save(receiver);

        ChatRoom chatRoom = new ChatRoom();

        chatRoomRepository.save(chatRoom);

        ChatMessage chatMessage = ChatMessage.builder()
                .sender(sender)
                .receiver(receiver)
                .chatRoom(chatRoom)
                .message("message")
                .build();

        chatMessageRepository.save(chatMessage);

        ChatMessageResponseDto chatMessageResponseDto = chatMessageService.findById(chatMessage.getId());

        assertThat(chatMessageResponseDto.getMessage()).isEqualTo("message");
        assertThat(chatMessageResponseDto.getSenderName()).isEqualTo(sender.getUsername());
        assertThat(chatMessageResponseDto.getReceiverName()).isEqualTo(receiver.getUsername());
    }

    @Test
    public void 메세지_추가() {
        //given
        User sender = User.builder()
                .username("sender")
                .userRole(UserRole.USER)
                .password("1")
                .profileImage(null)
                .build();

        User receiver = User.builder()
                .username("receiver")
                .userRole(UserRole.USER)
                .password("1")
                .profileImage(null)
                .build();

        userRepository.save(sender);
        userRepository.save(receiver);

        ChatRoom chatRoom = new ChatRoom();

        chatRoomRepository.save(chatRoom);

        ChatMessageAddRequestDto chatMessageAddRequestDto = ChatMessageAddRequestDto.builder()
                .senderId(sender.getId())
                .receiverId(receiver.getId())
                .chatRoomId(chatRoom.getId())
                .message("message")
                .build();

        //when
        chatMessageService.addMessage(chatMessageAddRequestDto);

        List<ChatMessage> all = chatMessageRepository.findAll();

        //then
        assertThat(all.get(0).getMessage()).isEqualTo("message");
    }

    @Test
    public void 채팅방_메세지찾기(){
        //given
        User sender = User.builder()
                .username("sender")
                .userRole(UserRole.USER)
                .password("1")
                .profileImage(null)
                .build();

        User receiver = User.builder()
                .username("receiver")
                .userRole(UserRole.USER)
                .password("1")
                .profileImage(null)
                .build();

        userRepository.save(sender);
        userRepository.save(receiver);

        ChatRoom chatRoom = new ChatRoom();

        chatRoomRepository.save(chatRoom);

        ChatMessageAddRequestDto chatMessageAddRequestDto = ChatMessageAddRequestDto.builder()
                .senderId(sender.getId())
                .receiverId(receiver.getId())
                .chatRoomId(chatRoom.getId())
                .message("message")
                .build();

        chatMessageService.addMessage(chatMessageAddRequestDto);

        //when
        List<ChatMessageResponseDto> chatRoomMessage = chatMessageService.findChatRoomMessage(chatRoom.getId());

        //then
        assertThat(chatRoomMessage.size()).isEqualTo(1);
        assertThat(chatRoomMessage.get(0).getMessage()).isEqualTo("message");
    }
}