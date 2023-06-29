package side.collectionrecord.domain.posts;

import java.util.List;

public interface PostsRepositoryCustom {
    List<Posts> findPostsList(Long userId, String categoryName, int offset, int size);

    List<Posts> findContainsHashtag(String hashtag);
}
