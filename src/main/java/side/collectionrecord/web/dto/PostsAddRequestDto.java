package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostsAddRequestDto {
    private Long userId;
    private Long categoryId;
    private String title;
    private String image;
    private String text;

    @Builder
    public PostsAddRequestDto(Long userId, Long categoryId, String title, String image, String text){
        this.userId = userId;
        this.categoryId = categoryId;
        this.title = title;
        this.image = image;
        this.text = text;
    }
}
