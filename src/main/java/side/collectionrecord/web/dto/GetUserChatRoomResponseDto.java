package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.user.User;

@Getter
@NoArgsConstructor
public class GetUserChatRoomResponseDto {
    private Long userId;
    private String username;
    private Long profileImageId;

    private boolean isRead;

    public GetUserChatRoomResponseDto(User user, boolean isRead){
        this.userId = user.getId();
        this.username = user.getUsername();
        this.profileImageId = user.getProfileImage().getId();
        this.isRead = isRead;
    }
}
