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
public class FollowPostsResponseDto {
    private String createdDate;
    private String username;
    private Long userId;
    private Long userProfileImageId;
    private Long id;
    private String artist;
    private String album;
    private String genre;
    private String albumArt;
    private Category category;
    private PostsStatus status;
    private List<Long> imageIds = new ArrayList<>();
    private String text;

    public FollowPostsResponseDto(Posts posts){
        this.createdDate = posts.getCreatedDate();
        this.userId = posts.getUser().getId();
        this.username = posts.getUser().getUsername();
        this.userProfileImageId = posts.getUser().getProfileImage().getId();
        this.id = posts.getId();
        this.artist = posts.getArtist();
        this.album = posts.getAlbum();
        this.genre = posts.getGenre();
        this.albumArt = posts.getAlbumArt();
        this.category = posts.getCategory();
        this.status = posts.getStatus();
        for (Image image : posts.getImages()){
            this.imageIds.add(image.getId());
        }
        this.text = posts.getText();
    }
}
