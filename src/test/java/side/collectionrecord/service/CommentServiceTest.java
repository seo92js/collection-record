package side.collectionrecord.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.category.CategoryRepository;
import side.collectionrecord.domain.comment.Comment;
import side.collectionrecord.domain.comment.CommentRepository;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.CommentAddRequestDto;
import side.collectionrecord.web.dto.CommentListResponseDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CommentServiceTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentService commentService;

    @Test
    public void 댓글추가(){
        //given
        User user = User.builder()
                .username("user")
                .password("1234")
                .profileImage(null)
                .build();

        userRepository.save(user);

        Category category = Category.builder()
                .user(user)
                .name("category")
                .build();

        categoryRepository.save(category);

        Posts posts = Posts.builder()
                .text("text")
                .representativeImage(null)
                .title("title")
                .user(user)
                .category(category)
                .build();

        postsRepository.save(posts);

        CommentAddRequestDto commentAddRequestDto = CommentAddRequestDto.builder()
                .userId(user.getId())
                .text("comment")
                .postsId(posts.getId())
                .build();

        //when
        commentService.addComment(commentAddRequestDto);

        //then
        List<Comment> all = commentRepository.findAll();

        assertThat(all.size()).isEqualTo(1);
        assertThat(all.get(0).getText()).isEqualTo("comment");
    }

    @Test
    public void 댓글삭제(){
        //given
        User user = User.builder()
                .username("user")
                .password("1234")
                .profileImage(null)
                .build();

        userRepository.save(user);

        Category category = Category.builder()
                .user(user)
                .name("category")
                .build();

        categoryRepository.save(category);

        Posts posts = Posts.builder()
                .text("text")
                .representativeImage(null)
                .title("title")
                .user(user)
                .category(category)
                .build();

        postsRepository.save(posts);

        CommentAddRequestDto commentAddRequestDto = CommentAddRequestDto.builder()
                .userId(user.getId())
                .text("comment")
                .postsId(posts.getId())
                .build();

        Long id = commentService.addComment(commentAddRequestDto);

        //when
        commentService.deleteComment(id);

        //then
        List<Comment> all = commentRepository.findAll();

        assertThat(all.size()).isEqualTo(0);
    }

    @Test
    public void 모든댓글찾기(){
        //given
        User user = User.builder()
                .username("user")
                .password("1234")
                .profileImage(null)
                .build();

        userRepository.save(user);

        Category category = Category.builder()
                .user(user)
                .name("category")
                .build();

        categoryRepository.save(category);

        Posts posts = Posts.builder()
                .text("text")
                .representativeImage(null)
                .title("title")
                .user(user)
                .category(category)
                .build();

        postsRepository.save(posts);

        CommentAddRequestDto commentAddRequestDto1 = CommentAddRequestDto.builder()
                .userId(user.getId())
                .text("comment1")
                .postsId(posts.getId())
                .build();

        CommentAddRequestDto commentAddRequestDto2 = CommentAddRequestDto.builder()
                .userId(user.getId())
                .text("comment2")
                .postsId(posts.getId())
                .build();

        commentService.addComment(commentAddRequestDto1);
        commentService.addComment(commentAddRequestDto2);

        //when
        List<CommentListResponseDto> comments = commentService.findComments(posts);

        //then
        assertThat(comments.size()).isEqualTo(2);
        assertThat(comments.get(0).getText()).isEqualTo("comment1");
        assertThat(comments.get(1).getText()).isEqualTo("comment2");
    }
}