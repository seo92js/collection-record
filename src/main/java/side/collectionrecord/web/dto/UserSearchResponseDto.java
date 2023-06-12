package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.user.User;

@Getter
@NoArgsConstructor
public class UserSearchResponseDto {
    private String image;
    private String username;

    public UserSearchResponseDto(User user){
        this.image = user.getImage();
        this.username = user.getUsername();
    }
}
