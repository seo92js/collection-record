package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {
    private String categoryName;
    private String title;
    private String image;
    private String text;
    private String hashtags;

    @Builder
    public PostsUpdateRequestDto(String categoryName, String title, String image, String text, String hashtags){
        this.categoryName = categoryName;
        this.title = title;
        this.image = image;
        this.text = text;
        this.hashtags = hashtags;
    }
}
