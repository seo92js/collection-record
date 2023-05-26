package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private Long categoryId;
    private String title;
    private String image;
    private String text;

    @Builder
    public PostsUpdateRequestDto(Long categoryId, String title, String image, String text){
        this.categoryId = categoryId;
        this.title = title;
        this.image = image;
        this.text = text;
    }
}
