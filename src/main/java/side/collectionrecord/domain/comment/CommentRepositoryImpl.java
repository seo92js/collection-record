package side.collectionrecord.domain.comment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import side.collectionrecord.domain.posts.Posts;

import javax.persistence.EntityManager;
import java.util.List;

import static side.collectionrecord.domain.comment.QComment.comment;

public class CommentRepositoryImpl implements CommentRepositoryCustom{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public CommentRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Comment> findAllComments(Posts posts){
        return queryFactory.selectFrom(comment)
                .where(comment.posts.eq(posts))
                .orderBy(comment.createdDate.desc())
                .fetch();
    }
}
