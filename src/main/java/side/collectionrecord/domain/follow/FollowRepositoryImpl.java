package side.collectionrecord.domain.follow;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import side.collectionrecord.domain.posts.Posts;

import javax.persistence.EntityManager;
import java.util.List;

import static side.collectionrecord.domain.follow.QFollow.follow;
import static side.collectionrecord.domain.posts.QPosts.posts;

public class FollowRepositoryImpl implements FollowRepositoryCustom{
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public FollowRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Posts> findPostsByUserIdEqFollowingId(Long userId, Pageable pageable) {

        return queryFactory.selectFrom(posts)
                .join(follow)
                .on(
                        posts.user.id.eq(follow.follower.id)
                                .and(follow.following.id.eq(userId))
                )
                .orderBy(posts.createdDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

    }
}
