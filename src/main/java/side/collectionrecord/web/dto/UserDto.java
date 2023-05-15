package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String profileImage;

    @Builder
    public UserDto(String username, String password, String email, String profileImage){
        this.username = username;
        this.password = password;
        this.email = email;
        this.profileImage = profileImage;
    }
}
