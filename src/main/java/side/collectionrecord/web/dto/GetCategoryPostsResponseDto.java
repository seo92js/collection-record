package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsStatus;

@Getter
@NoArgsConstructor
public class GetCategoryPostsResponseDto {
    private Long id;
    private Long representativeImageId;
    private String title;
    private String createdDate;
    private PostsStatus status;

    public GetCategoryPostsResponseDto(Posts posts){
        this.id = posts.getId();
        // 일단 맨 앞 사진?
        this.representativeImageId = posts.getRepresentativeImage().get(0).getId();
        this.title = posts.getTitle();
        this.createdDate = posts.getCreatedDate();
        this.status = posts.getStatus();
    }
}
