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
        // 일단 맨 앞 사진?
        this.representativeImageId = posts.getRepresentativeImage().get(0).getId();
        this.title = posts.getTitle();
    }
}
