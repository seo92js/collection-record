package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.user.User;

@Getter
@NoArgsConstructor
public class GetUserProfileResponseDto {
    private String username;
    private byte[] profileImage;
    private String profileText;

    public GetUserProfileResponseDto(User user){
        this.username = user.getUsername();

        if (user.getProfileImage() != null){
            this.profileImage = user.getProfileImage().getData();
        }else{
            this.profileImage = null;
        }

        this.profileText = user.getProfileText();
    }
}
