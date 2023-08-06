package side.collectionrecord.domain.follow;

import side.collectionrecord.domain.posts.Posts;

import java.util.List;

public interface FollowRepositoryCustom {
    public List<Posts> findPostsByUserIdEqFollowingId(Long userId, int offset, int size);
}
