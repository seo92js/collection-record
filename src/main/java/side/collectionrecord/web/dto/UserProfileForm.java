package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileForm {
    @NotBlank
    @Size(min = 3, max = 15, message = "3글자 ~ 15글자 사이만 입력 가능합니다.")
    @Pattern(regexp = "^[a-z0-9]*$", message = "알파벳 소문자(a-z), 숫자(0-9)만 입력 가능합니다.")
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
