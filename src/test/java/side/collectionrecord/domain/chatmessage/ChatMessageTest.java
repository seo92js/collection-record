package side.collectionrecord.domain.chatmessage;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import side.collectionrecord.domain.chatroom.ChatRoom;
import side.collectionrecord.domain.user.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChatMessageTest {
    @Test
    public void 연관관계_세팅(){
        //given
        User user = User.builder()
                .username("user")
                .password("1")
                .profileImage(null)
                .build();

        ChatRoom chatRoom = new ChatRoom();

        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoom(chatRoom)
                .user(user)
                .message("message")
                .build();

        //when
        //then
        assertThat(chatMessage.getUser()).isEqualTo(user);
        assertThat(user.getChatMessages()).containsExactly(chatMessage);
    }

    @Test
    public void 빌더패턴(){
        //given
        User user = User.builder()
                .username("user")
                .password("1")
                .profileImage(null)
                .build();

        ChatRoom chatRoom = new ChatRoom();

        ChatMessage chatMessage = ChatMessage.builder()
                .chatRoom(chatRoom)
                .user(user)
                .message("message")
                .build();

        //when
        //then
        assertThat(chatMessage.getMessage()).isEqualTo("message");
        assertThat(chatMessage.getUser()).isEqualTo(user);
        assertThat(chatMessage.getChatRoom()).isEqualTo(chatRoom);

    }
}