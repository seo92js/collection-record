package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserJoinDto {
    private String username;
    private String password;
    private String email;
    private String profile;

    @Builder
    public UserJoinDto(String username, String password, String email, String profile){
        this.username = username;
        this.password = password;
        this.email = email;
        this.profile = profile;
    }
}
