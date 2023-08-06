package side.collectionrecord.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.web.dto.CreateImageRequestDto;

@Getter
@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;

    @Transactional
    public Long createImage(CreateImageRequestDto createImageRequestDto){

        Image image = Image.builder()
                .filename(createImageRequestDto.getFilename())
                .data(createImageRequestDto.getData())
                .build();

        return imageRepository.save(image).getId();
    }

    @Transactional
    public Image getImageById(Long id){
        return imageRepository.findById(id).get();
    }
}
