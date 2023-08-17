package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.service.ImageService;
import side.collectionrecord.service.PostsService;
import side.collectionrecord.web.dto.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    private final ImageService imageService;

    @PostMapping("/api/v1/posts")
    public Long createPosts(@RequestPart(value = "createPostsRequestDto") CreatePostsRequestDto createPostsRequestDto, @RequestPart(value = "imageFile", required = true) List<MultipartFile> imageFiles) throws IOException {

        List<Long> imageIds = new ArrayList<>();

        for (MultipartFile imageFile : imageFiles){
            byte[] image = imageFile.getBytes();
            Long imageId = imageService.createImage(CreateImageRequestDto.builder()
                    .filename(imageFile.getOriginalFilename())
                    .data(image)
                    .build());

            imageIds.add(imageId);
        }

        List<Image> images = new ArrayList<>();

        for (Long imageId : imageIds){
            Image image = imageService.getImageById(imageId);
            images.add(image);
        }

        createPostsRequestDto.setRepresentativeImage(images);

        return postsService.createPosts(createPostsRequestDto);
    }

    @GetMapping("/api/v1/posts/{id}/{category}/{page}")
    public List<GetCategoryPostsResponseDto> getAllPostsByCategoryName(@PathVariable Long id, @PathVariable String category, @PathVariable int page){
        return postsService.getAllPostsByCategory(id, category, page, 9);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long updatePosts(@PathVariable Long id, @RequestBody UpdatePostsRequestDto updatePostsRequestDto) {
        Posts posts = postsService.getPostsById(id);

        Long userId = posts.getUser().getId();

        return postsService.updatePosts(userId, id, updatePostsRequestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long deletePosts(@PathVariable Long id){
        postsService.deletePosts(id);

        return id;
    }
}
