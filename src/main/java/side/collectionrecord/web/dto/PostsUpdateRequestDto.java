package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.posts.PostsStatus;

@Getter
@Setter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String categoryName;
    private String title;
    private Image representativeImage;
    private String text;
    private String hashtags;
    private PostsStatus status;

    @Builder
    public PostsUpdateRequestDto(String categoryName, String title, Image representativeImage, String text, String hashtags, PostsStatus status){
        this.categoryName = categoryName;
        this.title = title;
        this.representativeImage = representativeImage;
        this.text = text;
        this.hashtags = hashtags;
        this.status = status;
    }
}
