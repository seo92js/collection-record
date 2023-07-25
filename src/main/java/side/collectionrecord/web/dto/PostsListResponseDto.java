package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsStatus;

@Getter
@NoArgsConstructor
public class PostsListResponseDto {
    private Long id;
    private Long representativeImageId;
    private String title;
    private String createdDate;
    private PostsStatus status;

    public PostsListResponseDto(Posts posts){
        this.id = posts.getId();
        this.representativeImageId = posts.getRepresentativeImage().getId();
        this.title = posts.getTitle();
        this.createdDate = posts.getCreatedDate();
        this.status = posts.getStatus();
    }
}
