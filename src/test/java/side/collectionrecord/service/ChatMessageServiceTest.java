package side.collectionrecord.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.chatmessage.ChatMessage;
import side.collectionrecord.domain.chatmessage.ChatMessageRepository;
import side.collectionrecord.domain.chatroom.ChatRoom;
import side.collectionrecord.domain.chatroom.ChatRoomRepository;
import side.collectionrecord.domain.user.Role;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.CreateChatMessageRequestDto;
import side.collectionrecord.web.dto.GetChatMessageResponseDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
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
                .role(Role.USER)
                .profileImage(null)
                .profileText(null)
                .email("email")
                .build();

        User receiver = User.builder()
                .username("user")
                .role(Role.USER)
                .profileImage(null)
                .profileText(null)
                .email("email")
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

        GetChatMessageResponseDto getChatMessageResponseDto = chatMessageService.getChatMessageById(chatMessage.getId());

        assertThat(getChatMessageResponseDto.getMessage()).isEqualTo("message");
        assertThat(getChatMessageResponseDto.getSenderName()).isEqualTo(sender.getUsername());
        assertThat(getChatMessageResponseDto.getReceiverName()).isEqualTo(receiver.getUsername());
    }

    @Test
    public void 메세지_추가() {
        //given
        User sender = User.builder()
                .username("sender")
                .role(Role.USER)
                .profileImage(null)
                .profileText(null)
                .email("email")
                .build();

        User receiver = User.builder()
                .username("receiver")
                .role(Role.USER)
                .profileImage(null)
                .profileText(null)
                .email("email")
                .build();

        userRepository.save(sender);
        userRepository.save(receiver);

        ChatRoom chatRoom = new ChatRoom();

        chatRoomRepository.save(chatRoom);

        CreateChatMessageRequestDto createChatMessageRequestDto = CreateChatMessageRequestDto.builder()
                .senderId(sender.getId())
                .receiverId(receiver.getId())
                .chatRoomId(chatRoom.getId())
                .message("message")
                .read(false)
                .build();

        //when
        chatMessageService.createChatMessage(createChatMessageRequestDto);

        List<ChatMessage> all = chatMessageRepository.findAll();

        //then
        assertThat(all.get(0).getMessage()).isEqualTo("message");
    }

    @Test
    public void 채팅방_메세지찾기(){
        //given
        User sender = User.builder()
                .username("sender")
                .role(Role.USER)
                .profileImage(null)
                .profileText(null)
                .email("email")
                .build();

        User receiver = User.builder()
                .username("receiver")
                .role(Role.USER)
                .profileImage(null)
                .profileText(null)
                .email("email")
                .build();

        userRepository.save(sender);
        userRepository.save(receiver);

        ChatRoom chatRoom = new ChatRoom();

        chatRoomRepository.save(chatRoom);

        CreateChatMessageRequestDto createChatMessageRequestDto = CreateChatMessageRequestDto.builder()
                .senderId(sender.getId())
                .receiverId(receiver.getId())
                .chatRoomId(chatRoom.getId())
                .message("message")
                .read(false)
                .build();

        chatMessageService.createChatMessage(createChatMessageRequestDto);

        //when
        List<GetChatMessageResponseDto> getChatMessageResponseDto = chatMessageService.getChatMessageByChatRoomId(chatRoom.getId());

        //then
        assertThat(getChatMessageResponseDto.size()).isEqualTo(1);
        assertThat(getChatMessageResponseDto.get(0).getMessage()).isEqualTo("message");
    }
}