package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateChildCommentRequestDto {
    private Long userId;
    private Long postsId;
    private String text;
    private Long parentCommentId;

    @Builder
    public CreateChildCommentRequestDto(Long userId, Long postsId, Long parentCommentId, String text){
        this.userId = userId;
        this.postsId = postsId;
        this.parentCommentId = parentCommentId;
        this.text = text;
    }
}
