package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentParentForm {
    private Long userId;
    private Long postsId;
    private String text;

    @Builder
    public CommentParentForm(Long userId, Long postsId, String text){
        this.userId = userId;
        this.postsId = postsId;
        this.text = text;
    }
}
