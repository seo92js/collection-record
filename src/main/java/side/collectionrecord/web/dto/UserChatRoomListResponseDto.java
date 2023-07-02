package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.user.User;

@Getter
@NoArgsConstructor
public class UserChatRoomListResponseDto {
    private Long userId;
    private String username;

    public UserChatRoomListResponseDto(User user){
        this.userId = user.getId();
        this.username = user.getUsername();
    }
}
