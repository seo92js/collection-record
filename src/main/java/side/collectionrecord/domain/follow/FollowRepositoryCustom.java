package side.collectionrecord.domain.follow;

import org.springframework.data.domain.Pageable;
import side.collectionrecord.domain.posts.Posts;

import java.util.List;

public interface FollowRepositoryCustom {

    public List<Posts> findPostsByUserIdEqFollowingId(Long userId, Pageable pageable);
}
