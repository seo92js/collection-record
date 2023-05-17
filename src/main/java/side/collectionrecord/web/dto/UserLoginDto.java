package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginDto {
    private String email;
    private String password;

    @Builder
    public UserLoginDto(String email, String password){
        this.email = email;
        this.password = password;
    }
}
