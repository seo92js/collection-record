package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.posts.PostsStatus;

@NoArgsConstructor
@Getter
@Setter
public class PostsAddRequestDto {
    private Long userId;
    private String categoryName;
    private String title;
    private Image representativeImage;
    private String text;

    private String hashtags;
    private PostsStatus status;

    @Builder
    public PostsAddRequestDto(Long userId, String categoryName, String title, Image representativeImage, String text, String hashtags, PostsStatus status){
        this.userId = userId;
        this.categoryName = categoryName;
        this.title = title;
        this.representativeImage = representativeImage;
        this.text = text;
        this.hashtags = hashtags;
        this.status = status;
    }
}
