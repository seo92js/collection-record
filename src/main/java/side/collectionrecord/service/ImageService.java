package side.collectionrecord.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.web.dto.ImageUploadRequestDto;

@Getter
@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public Long upload(ImageUploadRequestDto imageUploadRequestDto){

        Image image = Image.builder()
                .data(imageUploadRequestDto.getData())
                .build();

        return imageRepository.save(image).getId();
    }
}
