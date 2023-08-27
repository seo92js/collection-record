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
public class GetFollowPostsResponseDto {
    private String createdDate;
    private String username;
    private Long userProfileImageId;
    private Long id;
    private String artist;
    private String album;
    private String genre;
    private String albumArt;
    private Category category;
    private PostsStatus status;
    private List<Long> representativeImageId = new ArrayList<>();
    private String text;

    public GetFollowPostsResponseDto(Posts posts){
        this.createdDate = posts.getCreatedDate();
        this.username = posts.getUser().getUsername();
        this.userProfileImageId = posts.getUser().getProfileImage().getId();
        this.id = posts.getId();
        this.artist = posts.getArtist();
        this.album = posts.getAlbum();
        this.genre = posts.getGenre();
        this.albumArt = posts.getAlbumArt();
        this.category = posts.getCategory();
        // 일단 맨 앞 사진?
        //this.representativeImageId = posts.getRepresentativeImage().get(0).getId();
        this.status = posts.getStatus();
        for (Image image : posts.getRepresentativeImage()){
            this.representativeImageId.add(image.getId());
        }
        this.text = posts.getText();
    }
}
