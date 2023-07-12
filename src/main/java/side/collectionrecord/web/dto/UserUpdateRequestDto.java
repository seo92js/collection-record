package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import side.collectionrecord.domain.image.Image;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String username;
    private String password;
    private Image profileImage;

    @Builder
    public UserUpdateRequestDto(String username, String password, Image profileImage){
        this.username = username;
        this.password = password;
        this.profileImage = profileImage;
    }

    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(this.password);
    }
}
