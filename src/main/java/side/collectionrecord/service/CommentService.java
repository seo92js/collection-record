package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.comment.Comment;
import side.collectionrecord.domain.comment.CommentRepository;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.exception.CommentNotFoundException;
import side.collectionrecord.exception.PostsNotFoundException;
import side.collectionrecord.exception.UserNotFoundException;
import side.collectionrecord.web.dto.CommentChildForm;
import side.collectionrecord.web.dto.CommentParentForm;
import side.collectionrecord.web.dto.CommentResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostsRepository postsRepository;

    @Transactional
    public Long commentParentAdd(CommentParentForm commentParentForm){
        User user = userRepository.findById(commentParentForm.getUserId()).orElseThrow(() -> new UserNotFoundException("유저가 없습니다."));

        Posts posts = postsRepository.findById(commentParentForm.getPostsId()).orElseThrow(() -> new PostsNotFoundException("게시물이 없습니다."));

        return commentRepository.save(Comment.builder()
                        .user(user)
                        .posts(posts)
                        .parentComment(null)
                        .text(commentParentForm.getText())
                        .build())
                        .getId();
    }

    @Transactional
    public Long commentChildAdd(CommentChildForm commentChildForm){
        User user = userRepository.findById(commentChildForm.getUserId()).orElseThrow(() -> new UserNotFoundException("유저가 없습니다."));

        Posts posts = postsRepository.findById(commentChildForm.getPostsId()).orElseThrow(() -> new PostsNotFoundException("게시물이 없습니다."));

        Comment parentComment = commentRepository.findById(commentChildForm.getParentCommentId()).orElseThrow(() -> new CommentNotFoundException("댓글이 없습니다."));

        return commentRepository.save(Comment.builder()
                        .user(user)
                        .posts(posts)
                        .text(commentChildForm.getText())
                        .parentComment(parentComment)
                        .build())
                        .getId();
    }

    @Transactional
    public void deleteComment(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("댓글이 없습니다."));

        commentRepository.delete(comment);
    }

    @Transactional
    public List<CommentResponseDto> getAllParentCommentsByPosts(Posts posts){

        return commentRepository.findParentCommentByPosts(posts).stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CommentResponseDto> getAllChildCommentsByPosts(Posts posts){

        return commentRepository.findChildCommentByPosts(posts).stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }
}
