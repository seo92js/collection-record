package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.posts.PostsStatus;

@Getter
@Setter
@NoArgsConstructor
public class UpdatePostsRequestDto {
    private Category category;
    private String artist;
    private String album;
    private String genre;
    private String albumArt;
    private String text;
    private PostsStatus status;

    @Builder
    public UpdatePostsRequestDto(Category category, String artist, String album, String genre, String albumArt, String text, PostsStatus status){
        this.category = category;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.albumArt = albumArt;
        this.text = text;
        this.status = status;
    }
}
