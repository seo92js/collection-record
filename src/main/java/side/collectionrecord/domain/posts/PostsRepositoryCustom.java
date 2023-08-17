package side.collectionrecord.domain.posts;

import java.util.List;

public interface PostsRepositoryCustom {
/*    List<Posts> findByUserIdAndCategory(Long userId, String categoryName, int offset, int size);

    List<Posts> findByHashtagContains(String hashtag, int offset, int size);*/

    List<Posts> findByUserIdAndArtist(Long userId, String artist, int offset, int size);
    List<Posts> findByUserIdAndCategory(Long userId, String category, int offset, int size);
}
