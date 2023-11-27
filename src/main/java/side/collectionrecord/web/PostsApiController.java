package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.service.PostsService;
import side.collectionrecord.web.dto.GetArtistPostsResponseDto;
import side.collectionrecord.web.dto.GetCategoryPostsResponseDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @GetMapping("/api/v1/posts/category/{id}/{category}/{page}")
    public List<GetCategoryPostsResponseDto> getAllPostsByCategoryName(@PathVariable Long id, @PathVariable String category, @PathVariable int page){
        return postsService.getAllPostsByUserIdAndCategory(id, category, page, 9);
    }

    @GetMapping("/api/v1/posts/artist/{id}/{artist}/{page}")
    public List<GetArtistPostsResponseDto> getAllPostsByUserIdAndArtist(@PathVariable Long id, @PathVariable String artist, @PathVariable int page){
        return postsService.getAllPostsByUserIdAndArtist(id, artist, page, 9);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long deletePosts(@PathVariable Long id){
        postsService.deletePosts(id);

        return id;
    }
}
