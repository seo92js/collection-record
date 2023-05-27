package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.posts.Posts;

@Getter
@NoArgsConstructor
public class PostsListResponseDto {
    private String image;
    private String title;

    public PostsListResponseDto(Posts posts){
        this.image = posts.getImage();
        this.title = posts.getTitle();
    }
}
