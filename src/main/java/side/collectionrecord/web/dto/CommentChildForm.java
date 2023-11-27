package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentChildForm {
    private Long userId;
    private Long postsId;
    private Long parentCommentId;
    private String text;

    @Builder
    public CommentChildForm(Long userId, Long postsId, Long parentCommentId, String text){
        this.userId = userId;
        this.postsId = postsId;
        this.parentCommentId = parentCommentId;
        this.text = text;
    }
}
