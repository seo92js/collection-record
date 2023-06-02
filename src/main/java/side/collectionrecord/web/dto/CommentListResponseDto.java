package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.comment.Comment;

@Getter
@NoArgsConstructor
public class CommentListResponseDto {
    String username;
    String text;

    public CommentListResponseDto(Comment comment){
        this.username = comment.getUser().getUsername();
        this.text = comment.getText();
    }
}
