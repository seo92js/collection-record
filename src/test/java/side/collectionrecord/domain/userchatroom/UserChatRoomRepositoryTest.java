package side.collectionrecord.domain.userchatroom;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import side.collectionrecord.domain.chatroom.ChatRoom;
import side.collectionrecord.domain.chatroom.ChatRoomRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserChatRoomRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    UserChatRoomRepository userChatRoomRepository;

    @Test
    public void save(){
        User user = User.builder()
                .username("user")
                .password("1")
                .profileImage(null)
                .build();

        userRepository.save(user);

        ChatRoom chatRoom = new ChatRoom();

        chatRoomRepository.save(chatRoom);

        UserChatRoom userChatRoom = UserChatRoom.builder()
                .user(user)
                .chatRoom(chatRoom)
                .build();

        userChatRoomRepository.save(userChatRoom);

        List<UserChatRoom> all = userChatRoomRepository.findAll();

        assertThat(all.size()).isEqualTo(1);
    }
}