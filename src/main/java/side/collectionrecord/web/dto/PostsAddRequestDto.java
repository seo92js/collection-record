package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.image.Image;

@NoArgsConstructor
@Getter
public class PostsAddRequestDto {
    private Long userId;
    private String categoryName;
    private String title;
    private Image representativeImage;
    private String text;

    private String hashtags;

    @Builder
    public PostsAddRequestDto(Long userId, String categoryName, String title, Image representativeImage, String text, String hashtags){
        this.userId = userId;
        this.categoryName = categoryName;
        this.title = title;
        this.representativeImage = representativeImage;
        this.text = text;
        this.hashtags = hashtags;
    }
}
