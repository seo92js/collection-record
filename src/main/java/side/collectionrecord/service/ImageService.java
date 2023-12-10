package side.collectionrecord.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.exception.ImageNotFoundException;
import side.collectionrecord.web.dto.ImageRequestDto;

@Getter
@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;

    @Transactional
    public Long createImage(ImageRequestDto imageRequestDto){

        Image image = Image.builder()
                .filename(imageRequestDto.getFilename())
                .data(imageRequestDto.getData())
                .build();

        return imageRepository.save(image).getId();
    }

    @Transactional
    public Image getImageById(Long id){
        return imageRepository.findById(id).orElseThrow(() -> new ImageNotFoundException("이미지가 없습니다."));
    }
}
