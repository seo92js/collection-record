package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.posts.Posts;

@Getter
@NoArgsConstructor
public class PostsViewResponseDto {
    String title;
    String image;
    String text;

    public PostsViewResponseDto(Posts posts){
        this.title = posts.getTitle();
        this.image = posts.getImage();
        this.text = posts.getText();
    }
}
