package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentAddRequestDto {
    private Long userId;
    private Long postsId;
    private String text;

    @Builder
    public CommentAddRequestDto(Long userId, Long postsId, String text){
        this.userId = userId;
        this.postsId = postsId;
        this.text = text;
    }
}
