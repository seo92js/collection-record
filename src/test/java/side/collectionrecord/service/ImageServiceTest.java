package side.collectionrecord.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.web.dto.CreateImageRequestDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ImageServiceTest {
    @Autowired
    ImageService imageService;

    @Autowired
    ImageRepository imageRepository;

    @Test
    public void 이미지_업로드(){
        //given
        CreateImageRequestDto createImageRequestDto = CreateImageRequestDto.builder()
                .filename("image")
                .data(null)
                .build();

        imageService.createImage(createImageRequestDto);

        //when
        List<Image> all = imageRepository.findAll();

        //then
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void 이미지_찾기(){

        //given
        CreateImageRequestDto createImageRequestDto = CreateImageRequestDto.builder()
                .filename("image")
                .data(null)
                .build();

        Long id = imageService.createImage(createImageRequestDto);

        Image findImage = imageService.getImageById(id);

        assertThat(findImage.getFilename()).isEqualTo("image");
    }
}