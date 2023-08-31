package side.collectionrecord.domain.userchatroom;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import side.collectionrecord.domain.chatroom.ChatRoom;
import side.collectionrecord.domain.user.User;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserChatRoomTest {
    @Test
    public void 연관관계_세팅(){
        User user = User.builder()
                .username("user")
                .profileImage(null)
                .build();

        ChatRoom chatRoom = new ChatRoom();

        UserChatRoom userChatRoom = UserChatRoom.builder()
                .user(user)
                .chatRoom(chatRoom)
                .build();

        assertThat(user.getUserChatRooms().get(0).getUser()).isEqualTo(user);
        assertThat(user.getUserChatRooms().get(0).getChatRoom()).isEqualTo(chatRoom);
    }
}