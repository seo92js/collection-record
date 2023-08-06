package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CreateFollowRequestDto {
    private Long userId;
    private Long followingUserId;

    @Builder
    public CreateFollowRequestDto(Long userId, Long followingUserId){
        this.userId = userId;
        this.followingUserId = followingUserId;
    }
}
