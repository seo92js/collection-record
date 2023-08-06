package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.user.User;

@Getter
@NoArgsConstructor
public class GetUserChatRoomResponseDto {
    private Long userId;
    private String username;

    private boolean isRead;

    public GetUserChatRoomResponseDto(User user, boolean isRead){
        this.userId = user.getId();
        this.username = user.getUsername();
        this.isRead = isRead;
    }
}
