package side.collectionrecord.domain.user;

import side.collectionrecord.web.dto.UserChatRoomListResponseDto;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findContainsUsername(String username, int offset, int size);
}
