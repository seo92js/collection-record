package side.collectionrecord.domain.posts;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

import static side.collectionrecord.domain.posts.QPosts.posts;

public class PostsRepositoryImpl implements PostsRepositoryCustom{
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public PostsRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Posts> findByUserIdAndArtist(Long userId, String artist, int offset, int size){
        if (Objects.equals(artist, "all")){
            return queryFactory.selectFrom(posts)
                    .where(posts.user.id.eq(userId))
                    .orderBy(posts.createdDate.desc())
                    .offset(offset)
                    .limit(size)
                    .fetch();
        }else{
            return queryFactory.selectFrom(posts)
                    .where(posts.user.id.eq(userId).and(posts.artist.eq(artist)))
                    .orderBy(posts.createdDate.desc())
                    .offset(offset)
                    .limit(size)
                    .fetch();
        }
    }

    @Override
    public List<Posts> findByUserIdAndCategory(Long userId, String category, int offset, int size){
        if (Objects.equals(category, "all")){
            return queryFactory.selectFrom(posts)
                    .where(posts.user.id.eq(userId))
                    .orderBy(posts.createdDate.desc())
                    .offset(offset)
                    .limit(size)
                    .fetch();
        }else{
            return queryFactory.selectFrom(posts)
                    .where(posts.user.id.eq(userId).and(posts.category.stringValue().eq(category)))
                    .orderBy(posts.createdDate.desc())
                    .offset(offset)
                    .limit(size)
                    .fetch();
        }
    }
}
