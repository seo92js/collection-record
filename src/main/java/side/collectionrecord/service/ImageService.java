package side.collectionrecord.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.web.dto.ImageUploadRequestDto;

@Getter
@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;

    @Transactional
    public Long upload(ImageUploadRequestDto imageUploadRequestDto){

        Image image = Image.builder()
                .filename(imageUploadRequestDto.getFilename())
                .data(imageUploadRequestDto.getData())
                .build();

        return imageRepository.save(image).getId();
    }

    @Transactional
    public Image findImage(Long id){
        return imageRepository.findById(id).get();
    }
}
