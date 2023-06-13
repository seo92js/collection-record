package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import side.collectionrecord.service.ImageService;
import side.collectionrecord.web.dto.ImageUploadRequestDto;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class ImageApiController {
    private final ImageService imageService;

    @PutMapping("/api/v1/image-upload")
    public Long upload(@RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        byte[] image = imageFile.getBytes();

        return imageService.upload(ImageUploadRequestDto.builder()
                            .data(image)
                            .build());
    }
}
