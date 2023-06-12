package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.service.PostsService;
import side.collectionrecord.service.UserService;
import side.collectionrecord.web.dto.PostsSearchResponseDto;
import side.collectionrecord.web.dto.SearchResponseDto;
import side.collectionrecord.web.dto.UserSearchResponseDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SearchApiController {
    private final UserService userService;
    private final PostsService postsService;
    @GetMapping("/api/v1/search/{text}")
    public SearchResponseDto search(@PathVariable String text){
/*        return userService.findContainsUsername(text);

        return postsService.findContainsHashtags(text);*/

        List<UserSearchResponseDto> userSearchList = userService.findContainsUsername(text);

        List<PostsSearchResponseDto> postsSearchList = postsService.findContainsHashtags(text);

        return new SearchResponseDto(userSearchList, postsSearchList);
    }
}
