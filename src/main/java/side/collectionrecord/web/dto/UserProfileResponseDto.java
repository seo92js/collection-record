package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.user.User;

@Getter
@NoArgsConstructor
public class UserProfileResponseDto {
    private String username;
    private String password;
    private String image;

    public UserProfileResponseDto(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.image = user.getImage();
    }
}
