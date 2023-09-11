package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class GetPostsResponseDto {
    Long id;
    String artist;
    String album;
    String genre;
    String albumArt;
    Category category;
    List<Long> imageIds = new ArrayList<>();
    String text;
    PostsStatus status;

    public GetPostsResponseDto(Posts posts){
        this.id = posts.getId();
        this.category = posts.getCategory();
        this.artist = posts.getArtist();
        this.album = posts.getAlbum();
        this.genre = posts.getGenre();
        this.albumArt = posts.getAlbumArt();
        for (Image image : posts.getImages()){
            this.imageIds.add(image.getId());
        }
        this.text = posts.getText();
        this.status = posts.getStatus();
    }
}
