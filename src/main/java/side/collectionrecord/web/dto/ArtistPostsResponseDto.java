package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsStatus;

@Getter
@NoArgsConstructor
public class ArtistPostsResponseDto {
    Long id;
    String createdDate;
    Category category;
    String artist;
    String album;
    String genre;
    String albumArt;
    PostsStatus status;

    public ArtistPostsResponseDto(Posts posts){
        this.id = posts.getId();
        this.createdDate = posts.getCreatedDate();
        this.category = posts.getCategory();
        this.artist = posts.getArtist();
        this.album = posts.getAlbum();
        this.genre = posts.getGenre();
        this.albumArt = posts.getAlbumArt();
        this.status = posts.getStatus();
    }
}
