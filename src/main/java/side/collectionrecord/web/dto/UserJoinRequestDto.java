package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserJoinRequestDto {
    private String username;
    private String password;

    @Builder
    public UserJoinRequestDto(String username, String password){
        this.username = username;
        this.password = password;
    }
}
