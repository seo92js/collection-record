package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import side.collectionrecord.domain.posts.PostsStatus;

@Getter
@Setter
@NoArgsConstructor
public class UpdatePostsRequestDto {
    private Long categoryId;
    private String title;
    private String text;
    private String hashtags;
    private PostsStatus status;

    @Builder
    public UpdatePostsRequestDto(Long categoryId, String title, String text, String hashtags, PostsStatus status){
        this.categoryId = categoryId;
        this.title = title;
        this.text = text;
        this.hashtags = hashtags;
        this.status = status;
    }
}
