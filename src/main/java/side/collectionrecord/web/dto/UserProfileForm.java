package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileForm {
    private String username;
    private String profileText;
    private MultipartFile profileImage;

    @Builder
    public UserProfileForm(String username, String profileText, MultipartFile profileImage) {
        this.username = username;
        this.profileText = profileText;
        this.profileImage = profileImage;
    }
}
