package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.user.User;

@Getter
@NoArgsConstructor
public class UserSearchResponseDto {
    //private String image;
    private Image profileImage;
    private String username;

    public UserSearchResponseDto(User user){
        this.profileImage = user.getProfileImage();
        this.username = user.getUsername();
    }
}
