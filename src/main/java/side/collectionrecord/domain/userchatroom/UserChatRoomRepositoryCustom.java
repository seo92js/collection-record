package side.collectionrecord.domain.userchatroom;

import side.collectionrecord.domain.user.User;

import java.util.List;

public interface UserChatRoomRepositoryCustom {
    UserChatRoom findUserChatRoom(Long user1Id, Long user2Id);

    List<User> findUserChatRoomList(Long userId);
}
