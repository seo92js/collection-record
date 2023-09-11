package side.collectionrecord.domain.image;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsStatus;
import side.collectionrecord.domain.user.User;

import java.util.ArrayList;
import java.util.List;

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
                .profileImage(profileImage)
                .build();

        Image image = Image.builder()
                .filename("image")
                .data(null)
                .build();

        List<Image> images = new ArrayList<>();
        images.add(image);

        Posts posts = Posts.builder()
                .artist("artist")
                .album("album")
                .genre("genre")
                .albumArt("albumArt")
                .category(Category.CD)
                .text("text")
                .images(images)
                .status(PostsStatus.SALE)
                .build();
        //when
        //then
        assertThat(user.getProfileImage().getId()).isEqualTo(profileImage.getId());
        assertThat(posts.getImages().get(0).getId()).isEqualTo(image.getId());
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