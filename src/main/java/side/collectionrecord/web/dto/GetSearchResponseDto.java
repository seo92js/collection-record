package side.collectionrecord.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetSearchResponseDto {
    private List<GetSearchUserResponseDto> userSearchList;
    private List<GetSearchPostsResponseDto> postsSearchList;

    public GetSearchResponseDto(List<GetSearchUserResponseDto> userSearchList, List<GetSearchPostsResponseDto> postsSearchList){
        this.userSearchList = userSearchList;
        this.postsSearchList = postsSearchList;
    }
}
