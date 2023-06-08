package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostsAddRequestDto {
    private Long userId;
    private String categoryName;
    private String title;
    private String image;
    private String text;

    @Builder
    public PostsAddRequestDto(Long userId, String categoryName, String title, String image, String text){
        this.userId = userId;
        this.categoryName = categoryName;
        this.title = title;
        this.image = image;
        this.text = text;
    }
}