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
import side.collectionrecord.web.dto.CommentAddRequestDto;
import side.collectionrecord.web.dto.CommentChildAddRequestDto;
import side.collectionrecord.web.dto.CommentListResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostsRepository postsRepository;

    @Transactional
    public Long addComment(CommentAddRequestDto commentAddRequestDto){

        User user = userRepository.findById(commentAddRequestDto.getUserId()).get();
        Posts posts = postsRepository.findById(commentAddRequestDto.getPostsId()).get();

        return commentRepository.save(Comment.builder()
                        .user(user)
                        .posts(posts)
                        .parentComment(null)
                        .text(commentAddRequestDto.getText())
                        .build())
                        .getId();
    }

    @Transactional
    public Long addCommentChild(CommentChildAddRequestDto commentChildAddRequestDto){
        User user = userRepository.findById(commentChildAddRequestDto.getUserId()).get();
        Posts posts = postsRepository.findById(commentChildAddRequestDto.getPostsId()).get();
        Comment parentComment = commentRepository.findById(commentChildAddRequestDto.getParentCommentId()).get();

        return commentRepository.save(Comment.builder()
                        .user(user)
                        .posts(posts)
                        .text(commentChildAddRequestDto.getText())
                        .parentComment(parentComment)
                        .build())
                        .getId();
    }

    @Transactional
    public void deleteComment(Long id){
        Comment comment = commentRepository.findById(id).orElse(null);

        commentRepository.delete(comment);
    }

    @Transactional
    public List<CommentListResponseDto> findParentComments(Posts posts){

        return commentRepository.findAllParentComments(posts).stream()
                .map(CommentListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CommentListResponseDto> findChildComments(Posts posts){

        return commentRepository.findAllChildComments(posts).stream()
                .map(CommentListResponseDto::new)
                .collect(Collectors.toList());
    }
}
