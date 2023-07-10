package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
