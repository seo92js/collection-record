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
    public List<Comment> findParentCommentByPosts(Posts posts){
        return queryFactory.selectFrom(comment)
                .where(comment.posts.eq(posts).and(comment.parentComment.isNull()))
                .orderBy(comment.createdDate.asc())
                .fetch();
    }

    @Override
    public List<Comment> findChildCommentByPosts(Posts posts){
        return queryFactory.selectFrom(comment)
                .where(comment.posts.eq(posts).and(comment.parentComment.isNotNull()))
                .orderBy(comment.createdDate.asc())
                .fetch();
    }
}
