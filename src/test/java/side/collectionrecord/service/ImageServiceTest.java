package side.collectionrecord.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.web.dto.ImageUploadRequestDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ImageServiceTest {
    @Autowired
    ImageService imageService;

    @Autowired
    ImageRepository imageRepository;

    @AfterEach
    public void cleanup(){
        imageRepository.deleteAll();
    }

    @Test
    public void 이미지_업로드(){
        //given
        ImageUploadRequestDto imageUploadRequestDto = ImageUploadRequestDto.builder()
                .filename("image")
                .data(null)
                .build();

        imageService.upload(imageUploadRequestDto);

        //when
        List<Image> all = imageRepository.findAll();

        //then
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void 이미지_찾기(){

        //given
        ImageUploadRequestDto imageUploadRequestDto = ImageUploadRequestDto.builder()
                .filename("image")
                .data(null)
                .build();

        Long id = imageService.upload(imageUploadRequestDto);

        Image findImage = imageService.findImage(id);

        assertThat(findImage.getFilename()).isEqualTo("image");
    }
}