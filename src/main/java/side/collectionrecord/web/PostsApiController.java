package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsStatus;
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
    public Long save(@RequestPart(value = "postsAddRequestDto") PostsAddRequestDto postsAddRequestDto, @RequestPart(value = "imageFile", required = true)MultipartFile imageFile) throws IOException {

        byte[] image = imageFile.getBytes();

        Long imageId = imageService.upload(ImageUploadRequestDto.builder()
                .filename(imageFile.getOriginalFilename())
                .data(image)
                .build());

        postsAddRequestDto.setRepresentativeImage(imageService.findImage(imageId));

        Long postsId = postsService.addPosts(postsAddRequestDto);

        return postsId;
    }

    @GetMapping("/api/v1/posts/{id}/{categoryName}")
    public List<PostsListResponseDto> findPostsList(@PathVariable Long id, @PathVariable String categoryName){
        return postsService.findPostsList(id, categoryName);
    }

    @PutMapping("/api/v1/posts-update/{id}")
    public Long update(@PathVariable Long id, @RequestPart(value = "postsUpdateRequestDto") PostsUpdateRequestDto postsUpdateRequestDto, @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        Long imageId;

        Posts posts = postsService.findPosts(id);

        if (imageFile != null){
            byte[] image = imageFile.getBytes();

            imageId = imageService.upload(ImageUploadRequestDto.builder()
                    .filename(imageFile.getOriginalFilename())
                    .data(image)
                    .build());
        }else{
            imageId = imageService.findImage(posts.getRepresentativeImage().getId()).getId();
        }

        postsUpdateRequestDto.setRepresentativeImage(imageService.findImage(imageId));

        Long userId = posts.getUser().getId();

        return postsService.update(userId, id, postsUpdateRequestDto);
    }

    @DeleteMapping("/api/v1/posts-delete/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);

        return id;
    }
}
