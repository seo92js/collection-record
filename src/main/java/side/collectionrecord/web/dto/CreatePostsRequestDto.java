package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.posts.PostsStatus;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CreatePostsRequestDto {
    private Long userId;
    private Category category;
    private String artist;
    private String album;
    private String genre;
    private String albumArt;
    private List<Image> images;
    private String text;
    private PostsStatus status;

    @Builder
    public CreatePostsRequestDto(Long userId, Category category, String artist, String album, String genre, String albumArt, List<Image> images, String text, PostsStatus status){
        this.userId = userId;
        this.category = category;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.albumArt = albumArt;
        this.images = images;
        this.text = text;
        this.status = status;
    }
}
