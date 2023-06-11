package side.collectionrecord.domain.follow;

import com.querydsl.jpa.impl.JPAQueryFactory;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.QPosts;
import side.collectionrecord.domain.user.QUser;

import javax.persistence.EntityManager;
import java.util.List;

import static side.collectionrecord.domain.follow.QFollow.follow;
import static side.collectionrecord.domain.posts.QPosts.posts;
import static side.collectionrecord.domain.user.QUser.user;

public class FollowRepositoryImpl implements FollowRepositoryCustom{
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public FollowRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Posts> findFollowPosts(Long userId) {
/*        List<Long> followUserId = queryFactory
                .select(follow.follower.id)
                .from(follow)
                .where(follow.following.id.eq(userId))
                .fetch();

        return queryFactory
                .selectFrom(posts)
                .where(posts.user.id.in(followUserId))
                .fetch();*/

        return queryFactory
                .selectFrom(posts)
                .join(posts.user, user)
                .join(user.follower, follow)
                .where(follow.following.id.eq(userId))
                .fetch();
    }
}
