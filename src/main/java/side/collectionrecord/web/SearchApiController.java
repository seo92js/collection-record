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
    @GetMapping("/api/v1/search/{text}/{page}")
    public SearchResponseDto search(@PathVariable String text, @PathVariable int page){

        List<UserSearchResponseDto> userSearchList = userService.findContainsUsername(text, page, 5);

        List<PostsSearchResponseDto> postsSearchList = postsService.findContainsHashtags(text, page, 5);

        //if (userSearchList.size() == 0 && postsSearchList.size() == 0){
        //    return null;
        //}else{
            return new SearchResponseDto(userSearchList, postsSearchList);
        //}
    }
}
