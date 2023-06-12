package side.collectionrecord.domain.posts;

import side.collectionrecord.domain.user.User;

import java.util.List;

public interface PostsRepositoryCustom {
    List<Posts> findPostsList(Long userId, String categoryName);

    List<Posts> findContainsHashtag(String hashtag);
}
