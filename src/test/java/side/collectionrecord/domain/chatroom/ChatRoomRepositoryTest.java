package side.collectionrecord.domain.chatroom;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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