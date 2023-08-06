package side.collectionrecord.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.chatroom.ChatRoom;
import side.collectionrecord.domain.chatroom.ChatRoomRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ChatRoomServiceTest {
    @Autowired
    ChatRoomRepository chatRoomRepository;
    @Autowired
    ChatRoomService chatRoomService;

    @Test
    public void 챗룸_추가(){
        //given
        //when
        chatRoomService.createChatRoom();
        chatRoomService.createChatRoom();

        List<ChatRoom> all = chatRoomRepository.findAll();

        //then
        assertThat(all.size()).isEqualTo(2);
    }
}