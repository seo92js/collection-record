package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import side.collectionrecord.service.PostsService;
import side.collectionrecord.web.dto.PostsAddRequestDto;
import side.collectionrecord.web.dto.PostsListResponseDto;
import side.collectionrecord.web.dto.PostsUpdateRequestDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsAddRequestDto postsAddRequestDto, HttpServletRequest request){
        HttpSession httpSession = request.getSession();

        Long userId = (Long) httpSession.getAttribute("userId");

        Long postsId = postsService.addPosts(userId, postsAddRequestDto);

        return postsId;
    }

    @GetMapping("/api/v1/posts/{categoryName}")
    public List<PostsListResponseDto> findPostsList(@PathVariable String categoryName, HttpServletRequest request){
        HttpSession httpSession = request.getSession();

        Long userId = (Long) httpSession.getAttribute("userId");

        return postsService.findPostsList(userId, categoryName);
    }

    @PutMapping("/api/v1/posts-update/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto postsUpdateRequestDto){

        Long userId = postsService.findPosts(id).getUser().getId();

        return postsService.update(userId, id, postsUpdateRequestDto);
    }
}
