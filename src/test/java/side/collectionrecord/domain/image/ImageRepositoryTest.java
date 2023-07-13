package side.collectionrecord.domain.image;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ImageRepositoryTest {

    @Autowired
    ImageRepository imageRepository;

    @Test
    public void save(){
        //given
        Image image = Image.builder()
                .filename("image")
                .data(null)
                .build();

        //when
        imageRepository.save(image);

        //then
        List<Image> all = imageRepository.findAll();

        assertThat(all.size()).isEqualTo(1);
    }
}