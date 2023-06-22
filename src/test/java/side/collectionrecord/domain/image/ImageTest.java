package side.collectionrecord.domain.image;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsStatus;
import side.collectionrecord.domain.user.User;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ImageTest {
    @Test
    public void 연관관계_세팅(){
        //given
        Image profileImage = Image.builder()
                .filename("profile")
                .data(null)
                .build();

        User user = User.builder()
                .username("user")
                .password("password")
                .profileImage(profileImage)
                .build();

        Category category = Category.builder()
                .name("category")
                .user(user)
                .build();

        Image representativeImage = Image.builder()
                .filename("representative")
                .data(null)
                .build();

        Posts posts = Posts.builder()
                .category(category)
                .title("title")
                .text("text")
                .representativeImage(representativeImage)
                .hashtags("hashtags")
                .status(PostsStatus.SALE)
                .build();
        //when
        //then
        assertThat(user.getProfileImage().getId()).isEqualTo(profileImage.getId());
        assertThat(posts.getRepresentativeImage().getId()).isEqualTo(representativeImage.getId());
    }

    @Test
    public void 빌더패턴(){
        //given
        Image image = Image.builder()
                .filename("image")
                .data(null)
                .build();

        //when
        //then
        assertThat(image.getFilename()).isEqualTo("image");
        assertThat(image.getData()).isNull();
    }
}