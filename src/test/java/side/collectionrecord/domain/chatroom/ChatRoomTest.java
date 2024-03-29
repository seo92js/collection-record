package side.collectionrecord.domain.chatroom;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import side.collectionrecord.domain.chatmessage.ChatMessage;
import side.collectionrecord.domain.user.User;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ChatRoomTest {

    @Test
    public void 연관관계_세팅(){
        //given
        User sender = User.builder()
                .username("sender")
                .profileImage(null)
                .build();

        User receiver = User.builder()
                .username("receiver")
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