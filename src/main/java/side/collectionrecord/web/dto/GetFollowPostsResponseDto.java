package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsStatus;

@Getter
@NoArgsConstructor
public class GetFollowPostsResponseDto {
    private String createdDate;
    private String username;
    private Long id;
    private String artist;
    private String album;
    private String genre;
    private String albumArt;
    private String title;
    //private Long representativeImageId;
    private PostsStatus status;

    public GetFollowPostsResponseDto(Posts posts){
        this.createdDate = posts.getCreatedDate();
        this.username = posts.getUser().getUsername();
        this.id = posts.getId();
        this.artist = posts.getArtist();
        this.album = posts.getAlbum();
        this.genre = posts.getGenre();
        this.albumArt = posts.getAlbumArt();
        // 일단 맨 앞 사진?
        //this.representativeImageId = posts.getRepresentativeImage().get(0).getId();
        this.status = posts.getStatus();
    }
}
