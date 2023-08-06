package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.service.PostsService;
import side.collectionrecord.service.UserService;
import side.collectionrecord.web.dto.GetSearchPostsResponseDto;
import side.collectionrecord.web.dto.GetSearchResponseDto;
import side.collectionrecord.web.dto.GetSearchUserResponseDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SearchApiController {
    private final UserService userService;
    private final PostsService postsService;
    @GetMapping("/api/v1/search/{text}/{page}")
    public GetSearchResponseDto getAllSearch(@PathVariable String text, @PathVariable int page){

        List<GetSearchUserResponseDto> userSearchList = userService.getAllUserByUsernameContains(text, page, 5);

        List<GetSearchPostsResponseDto> postsSearchList = postsService.getAllPostsByHashtagsContains(text, page, 5);

        return new GetSearchResponseDto(userSearchList, postsSearchList);
    }
}
