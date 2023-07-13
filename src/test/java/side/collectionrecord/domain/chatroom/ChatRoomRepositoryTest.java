package side.collectionrecord.domain.chatroom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ChatRoomRepositoryTest {
    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Test
    public void save(){
        //given
        ChatRoom chatRoom = new ChatRoom();

        chatRoomRepository.save(chatRoom);

        //when
        List<ChatRoom> all = chatRoomRepository.findAll();

        //then
        assertThat(all.size()).isEqualTo(1);
    }
}