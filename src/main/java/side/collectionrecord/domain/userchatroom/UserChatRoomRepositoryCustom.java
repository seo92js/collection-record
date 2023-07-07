package side.collectionrecord.domain.userchatroom;

import com.querydsl.core.Tuple;
import side.collectionrecord.domain.user.User;

import java.util.List;

public interface UserChatRoomRepositoryCustom {
    UserChatRoom findUserChatRoom(Long user1Id, Long user2Id);

    List<Tuple> findUserChatRoomList(Long userId);
}
