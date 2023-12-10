package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsStatus;

@Getter
@NoArgsConstructor
public class SearchArtistPostsResponseDto {
    private Long id;
    private String createdDate;
    private String username;
    private Category category;
    private String artist;
    private String album;
    private String genre;
    private String albumArt;
    private PostsStatus status;

    public SearchArtistPostsResponseDto(Posts posts){
        this.id = posts.getId();
        this.createdDate = posts.getCreatedDate();
        this.username = posts.getUser().getUsername();
        this.category = posts.getCategory();
        this.artist = posts.getArtist();
        this.album = posts.getAlbum();
        this.genre = posts.getGenre();
        this.albumArt = posts.getAlbumArt();
        this.status = posts.getStatus();
    }
}
