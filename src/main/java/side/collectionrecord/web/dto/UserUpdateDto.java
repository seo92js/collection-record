package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateDto {
    private String username;
    private String profile;

    @Builder
    public UserUpdateDto(String username, String profile){
        this.username = username;
        this.profile = profile;
    }
}
