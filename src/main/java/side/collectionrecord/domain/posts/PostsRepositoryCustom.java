package side.collectionrecord.domain.posts;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostsRepositoryCustom {
    List<Posts> findByArtistContains(String artist, Pageable pageable);
    List<Posts> findByUserIdAndArtist(Long userId, String artist, Pageable pageable);
    List<Posts> findByUserIdAndCategory(Long userId, String category, Pageable pageable);
    List<Posts> findByAlbumContains(String album, Pageable pageable);
    List<String> findArtistByUserId(Long userId);
}
