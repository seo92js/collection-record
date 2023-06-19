package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import side.collectionrecord.service.ImageService;
import side.collectionrecord.service.PostsService;
import side.collectionrecord.web.dto.ImageUploadRequestDto;
import side.collectionrecord.web.dto.PostsAddRequestDto;
import side.collectionrecord.web.dto.PostsListResponseDto;
import side.collectionrecord.web.dto.PostsUpdateRequestDto;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    private final ImageService imageService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestParam("userId") Long userId, @RequestParam("categoryName") String categoryName, @RequestParam("title") String title, @RequestParam(value = "imageFile", required = true)MultipartFile imageFile, @RequestParam("text") String text, @RequestParam("hashtags") String hashtags) throws IOException {

        byte[] image = imageFile.getBytes();

        Long imageId = imageService.upload(ImageUploadRequestDto.builder()
                .filename(imageFile.getOriginalFilename())
                .data(image)
                .build());

        PostsAddRequestDto postsAddRequestDto = PostsAddRequestDto.builder()
                .userId(userId)
                .categoryName(categoryName)
                .title(title)
                .representativeImage(imageService.findImage(imageId))
                .text(text)
                .hashtags(hashtags)
                .build();

        Long postsId = postsService.addPosts(postsAddRequestDto);

        return postsId;
    }

    @GetMapping("/api/v1/posts/{id}/{categoryName}")
    public List<PostsListResponseDto> findPostsList(@PathVariable Long id, @PathVariable String categoryName){
        return postsService.findPostsList(id, categoryName);
    }

    @PutMapping("/api/v1/posts-update/{id}")
    public Long update(@PathVariable Long id, @RequestParam("title") String title, @RequestParam("categoryName") String categoryName, @RequestParam("text") String text, @RequestParam("hashtags") String hashtags, @RequestParam(value = "imageFile", required = true) MultipartFile imageFile) throws IOException {

        byte[] image = imageFile.getBytes();

        Long imageId = imageService.upload(ImageUploadRequestDto.builder()
                .filename(imageFile.getOriginalFilename())
                .data(image)
                .build());

        PostsUpdateRequestDto postsUpdateRequestDto = PostsUpdateRequestDto.builder()
                .title(title)
                .categoryName(categoryName)
                .text(text)
                .hashtags(hashtags)
                .representativeImage(imageService.findImage(imageId))
                .build();

        Long userId = postsService.findPosts(id).getUser().getId();

        return postsService.update(userId, id, postsUpdateRequestDto);
    }
}
