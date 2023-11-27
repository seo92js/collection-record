package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.posts.PostsStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostsAddForm {
    private Long userId;
    private Category category;
    private String artist;
    private String album;
    private String genre;
    private String albumArt;
    private List<MultipartFile> images;
    private String text;
    private PostsStatus status;

    @Builder
    public PostsAddForm(Long userId, Category category, String artist, String album, String genre, String albumArt, List<MultipartFile> images, String text, PostsStatus status){
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
