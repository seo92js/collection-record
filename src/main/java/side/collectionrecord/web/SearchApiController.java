package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.service.PostsService;
import side.collectionrecord.service.UserService;
import side.collectionrecord.web.dto.SearchAlbumPostsResponseDto;
import side.collectionrecord.web.dto.SearchArtistPostsResponseDto;
import side.collectionrecord.web.dto.SearchUserResponseDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SearchApiController {
    private final UserService userService;
    private final PostsService postsService;

    @GetMapping("/api/v1/search/user/{text}/{page}")
    public List<SearchUserResponseDto> getAllSearchUser(@PathVariable String text, @PathVariable int page){

        PageRequest pageRequest = PageRequest.of(page, 5);

        return userService.getAllUserByUsernameContains(text, pageRequest);
    }

    @GetMapping("/api/v1/search/artist/{text}/{page}")
    public List<SearchArtistPostsResponseDto> getAllSearchArtistPosts(@PathVariable String text, @PathVariable int page) {

        PageRequest pageRequest = PageRequest.of(page, 5);

        return postsService.getAllPostsByArtistContains(text, pageRequest);
    }

    @GetMapping("/api/v1/search/album/{text}/{page}")
    public List<SearchAlbumPostsResponseDto> getAllSearchAlbumPosts(@PathVariable String text, @PathVariable int page){

        PageRequest pageRequest = PageRequest.of(page, 5);

        return postsService.getAllPostsByAlbumContains(text, pageRequest);
    }
}
