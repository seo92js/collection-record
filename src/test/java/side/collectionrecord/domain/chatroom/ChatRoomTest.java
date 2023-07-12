package side.collectionrecord.domain.chatroom;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import side.collectionrecord.domain.chatmessage.ChatMessage;
import side.collectionrecord.domain.user.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChatRoomTest {

    @Test
    public void 연관관계_세팅(){
        //given
        User sender = User.builder()
                .username("sender")
                .password("1")
                .profileImage(null)
                .build();

        User receiver = User.builder()
                .username("receiver")
                .password("1")
                .profileImage(null)
                .build();

        ChatRoom chatRoom = new ChatRoom();

        ChatMessage chatMessage = ChatMessage.builder()
                .sender(sender)
                .receiver(receiver)
                .chatRoom(chatRoom)
                .message("message")
                .build();
        //when
        //then
        assertThat(chatRoom.getChatMessages()).containsExactly(chatMessage);
        assertThat(chatMessage.getChatRoom()).isEqualTo(chatRoom);
    }
}