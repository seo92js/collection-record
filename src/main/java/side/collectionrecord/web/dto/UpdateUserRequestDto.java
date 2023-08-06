package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import side.collectionrecord.domain.image.Image;

@Getter
@Setter
@NoArgsConstructor
public class UpdateUserRequestDto {
    private String username;
    private Image profileImage;
    private String profileText;

    @Builder
    public UpdateUserRequestDto(String username, Image profileImage, String profileText){
        this.username = username;
        this.profileImage = profileImage;
        this.profileText = profileText;
    }

/*    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(this.password);
    }*/
}
