package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.posts.Posts;

@Getter
@NoArgsConstructor
public class PostsResponseDto {
    Long id;
    String categoryName;
    String title;
    private byte[] representativeImage;
    String text;
    String hashtags;

    public PostsResponseDto(Posts posts){
        this.id = posts.getId();
        this.categoryName = posts.getCategory().getName();
        this.title = posts.getTitle();
        this.representativeImage = posts.getRepresentativeImage().getData();
        this.text = posts.getText();
        this.hashtags = posts.getHashtags();
    }
}
