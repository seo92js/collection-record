package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserJoinRequestDto {
    @NotBlank(message = "빈 공간이 있습니다.")
    @Pattern(regexp = "^[a-z0-9]+$", message="소문자 a-z, 숫자 0-9 만 사용 가능합니다.")
    private String username;
    @NotBlank(message = "빈 공간이 있습니다.")
    @Pattern(regexp = "^[a-z0-9]+$", message="소문자 a-z, 숫자 0-9 만 사용 가능합니다.")
    @Size(min = 8, max = 15, message = "최소 8글자 최대 15글자 로 입력하세요.")
    private String password;

    @Builder
    public UserJoinRequestDto(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(this.password);
    }
}
