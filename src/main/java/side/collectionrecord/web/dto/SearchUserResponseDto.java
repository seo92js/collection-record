package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.user.User;

@Getter
@NoArgsConstructor
public class SearchUserResponseDto {
    private Long id;
    private Long profileImageId;
    private String username;

    public SearchUserResponseDto(User user){
        this.id = user.getId();
        this.profileImageId = user.getProfileImage().getId();
        this.username = user.getUsername();
    }
}
