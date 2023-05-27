package side.collectionrecord.domain.posts;

import com.querydsl.jpa.impl.JPAQueryFactory;
import side.collectionrecord.domain.category.Category;

import javax.persistence.EntityManager;
import java.util.List;

import static side.collectionrecord.domain.posts.QPosts.posts;

public class PostsRepositoryImpl implements PostsRepositoryCustom{
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public PostsRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }
}
