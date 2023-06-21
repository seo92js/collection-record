package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.user.User;

@Getter
@NoArgsConstructor
public class UserProfileResponseDto {
    private String username;
    private String password;
    private byte[] profileImage;

    public UserProfileResponseDto(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();

        if (user.getProfileImage() != null){
            this.profileImage = user.getProfileImage().getData();
        }else{
            this.profileImage = null;
        }
    }
}
