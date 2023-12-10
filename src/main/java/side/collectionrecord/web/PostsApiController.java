package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.service.PostsService;
import side.collectionrecord.web.dto.ArtistPostsResponseDto;
import side.collectionrecord.web.dto.CategoryPostsResponseDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @GetMapping("/api/v1/posts/category/{id}/{category}/{page}")
    public List<CategoryPostsResponseDto> getAllPostsByCategoryName(@PathVariable Long id, @PathVariable String category, @PathVariable int page){
        PageRequest pageRequest = PageRequest.of(page, 5);

        return postsService.getAllPostsByUserIdAndCategory(id, category, pageRequest);
    }

    @GetMapping("/api/v1/posts/artist/{id}/{artist}/{page}")
    public List<ArtistPostsResponseDto> getAllPostsByUserIdAndArtist(@PathVariable Long id, @PathVariable String artist, @PathVariable int page){

        PageRequest pageRequest = PageRequest.of(page, 9);

        return postsService.getAllPostsByUserIdAndArtist(id, artist, pageRequest);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long deletePosts(@PathVariable Long id){
        postsService.deletePosts(id);

        return id;
    }
}
