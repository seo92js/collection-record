package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.posts.Posts;

@Getter
@NoArgsConstructor
public class PostsSearchResponseDto {
    private Long id;
    private Long representativeImageId;
    private String title;

    public PostsSearchResponseDto(Posts posts){
        this.id = posts.getId();
        this.representativeImageId = posts.getRepresentativeImage().getId();
        this.title = posts.getTitle();
    }
}
