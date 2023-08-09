package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UpdateUserPasswordRequestDto {
    private String oldPassword;

    @NotBlank(message = "빈 공간이 있습니다.")
    @Pattern(regexp = "^[a-z0-9]+$", message="소문자 a-z, 숫자 0-9 만 사용 가능합니다.")
    @Size(min = 8, max = 15, message = "최소 8글자 최대 15글자 로 입력하세요.")
    private String newPassword;

    @Builder
    public UpdateUserPasswordRequestDto(String oldPassword, String newPassword){
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
