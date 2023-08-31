package side.collectionrecord.domain.chatmessage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import side.collectionrecord.domain.chatroom.ChatRoom;
import side.collectionrecord.domain.user.User;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ChatMessageTest {
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
                .chatRoom(chatRoom)
                .sender(sender)
                .receiver(receiver)
                .message("message")
                .build();

        //when
        //then
        assertThat(chatMessage.getSender()).isEqualTo(sender);
        assertThat(chatMessage.getReceiver()).isEqualTo(receiver);
        assertThat(sender.getSendMessage()).containsExactly(chatMessage);
        assertThat(receiver.getReceiveMessage()).containsExactly(chatMessage);
    }

    @Test
    public void 빌더패턴(){
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
                .chatRoom(chatRoom)
                .sender(sender)
                .receiver(receiver)
                .message("message")
                .build();

        //when
        //then
        assertThat(chatMessage.getMessage()).isEqualTo("message");
        assertThat(chatMessage.getSender()).isEqualTo(sender);
        assertThat(chatMessage.getChatRoom()).isEqualTo(chatRoom);
    }
}