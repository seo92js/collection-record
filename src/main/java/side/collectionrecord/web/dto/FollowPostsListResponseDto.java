package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsStatus;

@Getter
@NoArgsConstructor
public class FollowPostsListResponseDto {
    private String username;
    private Long id;
    private String title;
    private Long representativeImageId;
    private String createdDate;
    private PostsStatus status;

    public FollowPostsListResponseDto(Posts posts){
        this.username = posts.getUser().getUsername();
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.representativeImageId = posts.getRepresentativeImage().getId();
        this.createdDate = posts.getCreatedDate();
        this.status = posts.getStatus();
    }
}
