package side.collectionrecord.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.comment.Comment;
import side.collectionrecord.domain.comment.CommentRepository;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsRepository;
import side.collectionrecord.domain.posts.PostsStatus;
import side.collectionrecord.domain.user.Role;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.CommentParentForm;
import side.collectionrecord.web.dto.CommentResponseDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CommentServiceTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentService commentService;

    @Autowired
    ImageRepository imageRepository;

    @Test
    public void 댓글추가(){
        //given
        User user = User.builder()
                .username("user")
                .role(Role.USER)
                .profileImage(null)
                .profileText(null)
                .email("email")
                .build();

        userRepository.save(user);

        Posts posts = Posts.builder()
                .artist("artist")
                .album("album")
                .genre("genre")
                .albumArt("albumArt")
                .category(Category.CD)
                .text("text")
                .images(null)
                .user(user)
                .build();

        postsRepository.save(posts);

        CommentParentForm commentParentForm = CommentParentForm.builder()
                .userId(user.getId())
                .text("comment")
                .postsId(posts.getId())
                .build();

        //when
        commentService.commentParentAdd(commentParentForm);

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
                .role(Role.USER)
                .profileImage(null)
                .profileText(null)
                .email("email")
                .build();

        userRepository.save(user);

        Posts posts = Posts.builder()
                .text("text")
                .artist("artist")
                .album("album")
                .genre("genre")
                .albumArt("albumArt")
                .category(Category.CD)
                .images(null)
                .user(user)
                .build();

        postsRepository.save(posts);

        CommentParentForm commentParentForm = CommentParentForm.builder()
                .userId(user.getId())
                .text("comment")
                .postsId(posts.getId())
                .build();

        Long id = commentService.commentParentAdd(commentParentForm);

        //when
        commentService.deleteComment(id);

        //then
        List<Comment> all = commentRepository.findAll();

        assertThat(all.size()).isEqualTo(0);
    }

    @Test
    public void 모든댓글찾기(){
        //given
        Image image = Image.builder()
                .data(null)
                .filename("image")
                .build();

        imageRepository.save(image);

        User user = User.builder()
                .username("user")
                .role(Role.USER)
                .profileImage(image)
                .profileText(null)
                .email("email")
                .build();

        userRepository.save(user);

        Posts posts = Posts.builder()
                .text("text")
                .artist("artist")
                .album("album")
                .genre("genre")
                .albumArt("albumArt")
                .category(Category.CD)
                .images(null)
                .user(user)
                .status(PostsStatus.SALE)
                .build();

        postsRepository.save(posts);

        CommentParentForm commentParentForm1 = CommentParentForm.builder()
                .userId(user.getId())
                .text("comment1")
                .postsId(posts.getId())
                .build();

        CommentParentForm commentParentForm12 = CommentParentForm.builder()
                .userId(user.getId())
                .text("comment2")
                .postsId(posts.getId())
                .build();

        commentService.commentParentAdd(commentParentForm1);
        commentService.commentParentAdd(commentParentForm12);

        //when
        List<CommentResponseDto> comments = commentService.getAllParentCommentsByPosts(posts);

        //then
        assertThat(comments.size()).isEqualTo(2);
        assertThat(comments.get(0).getText()).isEqualTo("comment1");
        assertThat(comments.get(1).getText()).isEqualTo("comment2");
    }
}