package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import side.collectionrecord.domain.posts.PostsStatus;

@Getter
@Setter
@NoArgsConstructor
public class PostsUpdateForm {
    private String text;
    private PostsStatus status;

    @Builder
    public PostsUpdateForm(String text, PostsStatus status){
        this.text = text;
        this.status = status;
    }
}
