package side.collectionrecord.domain.comment;

import side.collectionrecord.domain.posts.Posts;

import java.util.List;

public interface CommentRepositoryCustom {
    public List<Comment> findParentCommentByPosts(Posts posts);
    public List<Comment> findChildCommentByPosts(Posts posts);
}
