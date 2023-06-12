package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SearchResponseDto {
    private List<UserSearchResponseDto> userSearchList;
    private List<PostsSearchResponseDto> postsSearchList;

    public SearchResponseDto(List<UserSearchResponseDto> userSearchList, List<PostsSearchResponseDto> postsSearchList){
        this.userSearchList = userSearchList;
        this.postsSearchList = postsSearchList;
    }
}
