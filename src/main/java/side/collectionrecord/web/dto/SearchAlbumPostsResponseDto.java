package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsStatus;

@Getter
@NoArgsConstructor
public class SearchAlbumPostsResponseDto {
    Long id;
    String createdDate;
    String username;
    Category category;
    String artist;
    String album;
    String genre;
    String albumArt;
    PostsStatus status;

    public SearchAlbumPostsResponseDto(Posts posts){
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
