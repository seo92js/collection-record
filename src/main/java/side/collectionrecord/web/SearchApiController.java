package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.service.PostsService;
import side.collectionrecord.service.UserService;
import side.collectionrecord.web.dto.GetSearchAlbumPostsResponseDto;
import side.collectionrecord.web.dto.GetSearchArtistPostsResponseDto;
import side.collectionrecord.web.dto.GetSearchUserResponseDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SearchApiController {
    private final UserService userService;
    private final PostsService postsService;
    @GetMapping("/api/v1/search/user/{text}/{page}")
    public List<GetSearchUserResponseDto> getAllSearchUser(@PathVariable String text, @PathVariable int page){

        return userService.getAllUserByUsernameContains(text, page, 5);
    }

    @GetMapping("/api/v1/search/artist/{text}/{page}")
    public List<GetSearchArtistPostsResponseDto> getAllSearchArtistPosts(@PathVariable String text, @PathVariable int page) {

        return postsService.getAllPostsByArtistContains(text, page, 5);
    }

    @GetMapping("/api/v1/search/album/{text}/{page}")
    public List<GetSearchAlbumPostsResponseDto> getAllSearchAlbumPosts(@PathVariable String text, @PathVariable int page){

        return postsService.getAllPostsByAlbumContains(text, page, 5);
    }
}
