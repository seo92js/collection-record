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
import side.collectionrecord.web.dto.CreateChildCommentRequestDto;
import side.collectionrecord.web.dto.CreateParentCommentRequestDto;
import side.collectionrecord.web.dto.GetCommentResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostsRepository postsRepository;

    @Transactional
    public Long createParentComment(CreateParentCommentRequestDto createParentCommentRequestDto){

        User user = userRepository.findById(createParentCommentRequestDto.getUserId()).get();
        Posts posts = postsRepository.findById(createParentCommentRequestDto.getPostsId()).get();

        return commentRepository.save(Comment.builder()
                        .user(user)
                        .posts(posts)
                        .parentComment(null)
                        .text(createParentCommentRequestDto.getText())
                        .build())
                        .getId();
    }

    @Transactional
    public Long createChildComment(CreateChildCommentRequestDto createChildCommentRequestDto){
        User user = userRepository.findById(createChildCommentRequestDto.getUserId()).get();
        Posts posts = postsRepository.findById(createChildCommentRequestDto.getPostsId()).get();
        Comment parentComment = commentRepository.findById(createChildCommentRequestDto.getParentCommentId()).get();

        return commentRepository.save(Comment.builder()
                        .user(user)
                        .posts(posts)
                        .text(createChildCommentRequestDto.getText())
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
    public List<GetCommentResponseDto> getAllParentCommentsByPosts(Posts posts){

        return commentRepository.findParentCommentByPosts(posts).stream()
                .map(GetCommentResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<GetCommentResponseDto> getAllChildCommentsByPosts(Posts posts){

        return commentRepository.findChildCommentByPosts(posts).stream()
                .map(GetCommentResponseDto::new)
                .collect(Collectors.toList());
    }
}
