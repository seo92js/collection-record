package side.collectionrecord.domain.posts;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

import static side.collectionrecord.domain.category.QCategory.category;
import static side.collectionrecord.domain.posts.QPosts.posts;

public class PostsRepositoryImpl implements PostsRepositoryCustom{
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public PostsRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Posts> findPostsList(Long userId, String categoryName, int offset, int size){

        if (Objects.equals(categoryName, "all")){
            return queryFactory.selectFrom(posts)
                    .where(posts.user.id.eq(userId))
                    .orderBy(posts.createdDate.desc())
                    .offset(offset)
                    .limit(size)
                    .fetch();
        }else{
            return queryFactory.selectFrom(posts)
                    .where(posts.user.id.eq(userId).and(category.name.eq(categoryName)))
                    .orderBy(posts.createdDate.desc())
                    .offset(offset)
                    .limit(size)
                    .fetch();
        }
    }

    @Override
    public List<Posts> findContainsHashtag(String hashtag, int offset, int size) {
        return queryFactory.selectFrom(posts)
                .where(posts.hashtags.contains(hashtag))
                .orderBy(posts.createdDate.desc())
                .offset(offset)
                .limit(size)
                .fetch();
    }
}
