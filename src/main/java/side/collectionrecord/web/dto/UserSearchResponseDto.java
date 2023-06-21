package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.user.User;

@Getter
@NoArgsConstructor
public class UserSearchResponseDto {
    private Long profileImageId;
    private String username;

    public UserSearchResponseDto(User user){
        this.profileImageId = user.getProfileImage().getId();
        this.username = user.getUsername();
    }
}
