package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.service.ImageService;

@RequiredArgsConstructor
@RestController
public class ImageApiController {
    private final ImageService imageService;

    @GetMapping("/api/v1/image-view/{id}")
    public ResponseEntity<byte[]> imageView(@PathVariable Long id){
        Image findImage = imageService.findImage(id);

        byte[] image = findImage.getData();

        HttpHeaders headers = new HttpHeaders();

        MediaType mediaType;

        String fileExtension = extractFileExtension(findImage.getFilename());

        ///이어서 수정
        if (fileExtension.equalsIgnoreCase("png")){
            mediaType = MediaType.IMAGE_PNG;
        } else if (fileExtension.equalsIgnoreCase("jpeg") || fileExtension.equalsIgnoreCase("jpg")) {
            mediaType = MediaType.IMAGE_JPEG;
        } else if (fileExtension.equalsIgnoreCase("gif")) {
            mediaType = MediaType.IMAGE_GIF;
        } else {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }

        headers.setContentType(mediaType); // 이미지 타입에 맞게 설정
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    private String extractFileExtension(String filename){
        int dotIndex = filename.lastIndexOf('.');

        if (dotIndex >= 0 && dotIndex < filename.length() - 1) {
            return filename.substring(dotIndex + 1).toLowerCase();
        }

        return "";
    }
}
