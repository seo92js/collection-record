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
    String image;
    String text;

    public PostsResponseDto(Posts posts){
        this.id = posts.getId();
        this.categoryName = posts.getCategory().getName();
        this.title = posts.getTitle();
        this.image = posts.getImage();
        this.text = posts.getText();
    }
}
