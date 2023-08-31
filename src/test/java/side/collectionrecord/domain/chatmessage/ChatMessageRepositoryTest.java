package side.collectionrecord.domain.chatmessage;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.chatroom.ChatRoom;
import side.collectionrecord.domain.chatroom.ChatRoomRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;

import java.util.List;

@SpringBootTest
@Transactional
class ChatMessageRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatMessageRepository chatMessageRepository;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Test
    public void 채팅방의_모든메세지_찾기(){
        //given
        User sender = User.builder()
                .userRole(UserRole.USER)
                .username("sender")
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

        ChatMessage chatMessage1 = ChatMessage.builder()
                .sender(sender)
                .receiver(receiver)
                .chatRoom(chatRoom)
                .message("1")
                .build();

        chatMessageRepository.save(chatMessage1);

        ChatMessage chatMessage2 = ChatMessage.builder()
                .sender(sender)
                .receiver(receiver)
                .chatRoom(chatRoom)
                .message("2")
                .build();

        chatMessageRepository.save(chatMessage2);

        //when
        List<ChatMessage> allMessageByChatRoom = chatMessageRepository.findByChatRoomId(chatRoom.getId());

        //then
        Assertions.assertThat(allMessageByChatRoom.size()).isEqualTo(2);
    }
}