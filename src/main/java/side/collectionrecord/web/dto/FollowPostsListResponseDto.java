package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.posts.Posts;

@Getter
@NoArgsConstructor
public class FollowPostsListResponseDto {
    private String username;
    private String title;
    private String image;

    public FollowPostsListResponseDto(Posts posts){
        this.username = posts.getUser().getUsername();
        this.title = posts.getTitle();
        this.image = posts.getImage();
    }
}
