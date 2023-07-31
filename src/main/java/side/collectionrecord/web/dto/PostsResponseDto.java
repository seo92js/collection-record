package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsStatus;

@Getter
@NoArgsConstructor
public class PostsResponseDto {
    Long id;
    String categoryName;
    String title;
    Long representativeImageId;

    String text;
    String hashtags;
    PostsStatus status;

    public PostsResponseDto(Posts posts){
        this.id = posts.getId();
        this.categoryName = posts.getCategory().getName();
        this.title = posts.getTitle();
        // 일단 맨 앞 사진?
        this.representativeImageId = posts.getRepresentativeImage().get(0).getId();
        this.text = posts.getText();
        this.hashtags = posts.getHashtags();
        this.status = posts.getStatus();
    }
}
