package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.user.User;

@Getter
@NoArgsConstructor
public class UserChatRoomResponseDto {
    private Long userId;
    private String username;
    private Long profileImageId;

    private boolean isConfirm;

    public UserChatRoomResponseDto(User user, boolean isConfirm){
        this.userId = user.getId();
        this.username = user.getUsername();
        this.profileImageId = user.getProfileImage().getId();
        this.isConfirm = isConfirm;
    }
}
