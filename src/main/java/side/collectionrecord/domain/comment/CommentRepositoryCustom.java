package side.collectionrecord.domain.comment;

import side.collectionrecord.domain.posts.Posts;

import java.util.List;

public interface CommentRepositoryCustom {
    public List<Comment> findAllParentComments(Posts posts);
    public List<Comment> findAllChildComments(Posts posts);
}
