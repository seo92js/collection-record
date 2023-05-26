package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.service.PostsService;
import side.collectionrecord.web.dto.PostsAddRequestDto;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsAddRequestDto postsAddRequestDto){
        Long id = postsService.addPosts(postsAddRequestDto);

        return id;
    }
}
