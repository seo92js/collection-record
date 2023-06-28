package side.collectionrecord.domain.chatmessage;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import side.collectionrecord.domain.chatroom.ChatRoom;
import side.collectionrecord.domain.chatroom.ChatRoomRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
        User user = User.builder()
                .username("username")
                .password("1")
                .profileImage(null)
                .build();

        userRepository.save(user);

        ChatRoom chatRoom = new ChatRoom();
        chatRoomRepository.save(chatRoom);

        ChatMessage chatMessage1 = ChatMessage.builder()
                .user(user)
                .chatRoom(chatRoom)
                .message("1")
                .build();

        chatMessageRepository.save(chatMessage1);

        ChatMessage chatMessage2 = ChatMessage.builder()
                .user(user)
                .chatRoom(chatRoom)
                .message("2")
                .build();

        chatMessageRepository.save(chatMessage2);

        //when
        List<ChatMessage> allMessageByChatRoom = chatMessageRepository.findAllMessageByChatRoom(chatRoom.getId());

        //then
        Assertions.assertThat(allMessageByChatRoom.size()).isEqualTo(2);
    }
}