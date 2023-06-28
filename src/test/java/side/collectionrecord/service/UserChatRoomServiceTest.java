package side.collectionrecord.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.domain.userchatroom.UserChatRoom;
import side.collectionrecord.domain.userchatroom.UserChatRoomRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserChatRoomServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserChatRoomRepository userChatRoomRepository;

    @Autowired
    UserChatRoomService userChatRoomService;

    @Test
    public void 채팅방_없으면_만들기(){
        //given
        User user1 = User.builder()
                .username("user1")
                .password("1")
                .profileImage(null)
                .build();

        userRepository.save(user1);

        User user2 = User.builder()
                .username("user2")
                .password("1")
                .profileImage(null)
                .build();

        userRepository.save(user2);

        //when
        UserChatRoom chatRoom = userChatRoomService.createChatRoom(user1.getId(), user2.getId());

        List<UserChatRoom> all = userChatRoomRepository.findAll();

        //then
        assertThat(chatRoom).isNotNull();

        assertThat(all.size()).isEqualTo(2);
    }
}