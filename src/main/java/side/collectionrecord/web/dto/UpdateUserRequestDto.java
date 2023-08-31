package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import side.collectionrecord.domain.image.Image;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserRequestDto {
    @NotBlank(message = "빈 공간이 있습니다.")
    @Pattern(regexp = "^[a-z0-9]+$", message="소문자 a-z, 숫자 0-9 만 사용 가능합니다.")
    private String username;
    private Image profileImage;
    private String profileText;

    @Builder
    public UpdateUserRequestDto(String username, Image profileImage, String profileText){
        this.username = username;
        this.profileImage = profileImage;
        this.profileText = profileText;
    }
}
