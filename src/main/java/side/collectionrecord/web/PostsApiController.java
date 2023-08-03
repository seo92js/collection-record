package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.service.ImageService;
import side.collectionrecord.service.PostsService;
import side.collectionrecord.web.dto.ImageUploadRequestDto;
import side.collectionrecord.web.dto.PostsAddRequestDto;
import side.collectionrecord.web.dto.PostsListResponseDto;
import side.collectionrecord.web.dto.PostsUpdateRequestDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    private final ImageService imageService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestPart(value = "postsAddRequestDto") PostsAddRequestDto postsAddRequestDto, @RequestPart(value = "imageFile", required = true) List<MultipartFile> imageFiles) throws IOException {

        List<Long> imageIds = new ArrayList<>();

        for (MultipartFile imageFile : imageFiles){
            byte[] image = imageFile.getBytes();
            Long imageId = imageService.upload(ImageUploadRequestDto.builder()
                    .filename(imageFile.getOriginalFilename())
                    .data(image)
                    .build());

            imageIds.add(imageId);
        }

        List<Image> images = new ArrayList<>();

        for (Long imageId : imageIds){
            Image image = imageService.findImage(imageId);
            images.add(image);
        }

        postsAddRequestDto.setRepresentativeImage(images);

        return postsService.createPosts(postsAddRequestDto);
    }

    @GetMapping("/api/v1/posts/{id}/{categoryName}/{page}")
    public List<PostsListResponseDto> findPostsList(@PathVariable Long id, @PathVariable String categoryName, @PathVariable int page){
        return postsService.getAllPostsByCategoryName(id, categoryName, page, 5);
    }

    @PutMapping("/api/v1/posts-update/{id}")
    public Long update(@PathVariable Long id, @RequestPart(value = "postsUpdateRequestDto") PostsUpdateRequestDto postsUpdateRequestDto, @RequestPart(value = "imageFile", required = false) List<MultipartFile> imageFiles) throws IOException {
        //Long imageId;
        List<Long> imageIds = new ArrayList<>();
        List<Image> images = new ArrayList<>();

        Posts posts = postsService.getPostsById(id);

        if (imageFiles != null){
            for (MultipartFile imageFile : imageFiles){
                byte[] image = imageFile.getBytes();

                Long imageId = imageService.upload(ImageUploadRequestDto.builder()
                        .filename(imageFile.getOriginalFilename())
                        .data(image)
                        .build());

                imageIds.add(imageId);
            }

            for (Long imageId : imageIds){
                Image image = imageService.findImage(imageId);
                images.add(image);
            }
        }else{
            images = posts.getRepresentativeImage();
        }

        postsUpdateRequestDto.setRepresentativeImage(images);

        Long userId = posts.getUser().getId();

        return postsService.updatePosts(userId, id, postsUpdateRequestDto);
    }

    @DeleteMapping("/api/v1/posts-delete/{id}")
    public Long delete(@PathVariable Long id){
        postsService.deletePosts(id);

        return id;
    }
}
