package side.collectionrecord.domain.userchatroom;

import com.querydsl.core.Tuple;

import java.util.List;

public interface UserChatRoomRepositoryCustom {
    UserChatRoom findByUserIds(Long user1Id, Long user2Id);

    List<Tuple> findUserAndChatroomIdByUserId(Long userId);
}
