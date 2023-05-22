package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String username;
    private String password;
    private String image;

    @Builder
    public UserUpdateRequestDto(String username, String password, String image){
        this.username = username;
        this.password = password;
        this.image = image;
    }
}
