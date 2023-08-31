package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.comment.Comment;

@Getter
@NoArgsConstructor
public class GetCommentResponseDto {
    Long id;
    Long parentId;
    Long profileImageId;
    String username;
    String text;
    String createdDate;

    public GetCommentResponseDto(Comment comment){
        this.id = comment.getId();
        this.profileImageId = comment.getUser().getProfileImage().getId();
        if (comment.getParentComment() != null)
            this.parentId = comment.getParentComment().getId();
        this.username = comment.getUser().getUsername();
        this.text = comment.getText();
        this.createdDate = comment.getCreatedDate();
    }
}
