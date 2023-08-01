package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostsResponseDto {
    Long id;
    String categoryName;
    String title;
    List<Long> representativeImageId = new ArrayList<>();
    String text;
    String hashtags;
    PostsStatus status;

    public PostsResponseDto(Posts posts){
        this.id = posts.getId();
        this.categoryName = posts.getCategory().getName();
        this.title = posts.getTitle();
        for (Image image : posts.getRepresentativeImage()){
            this.representativeImageId.add(image.getId());
        }
        this.text = posts.getText();
        this.hashtags = posts.getHashtags();
        this.status = posts.getStatus();
    }
}
