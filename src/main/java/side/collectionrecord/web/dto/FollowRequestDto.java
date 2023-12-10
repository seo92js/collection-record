package side.collectionrecord.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FollowRequestDto {
    private Long userId;
    private Long followingUserId;

    @Builder
    public FollowRequestDto(Long userId, Long followingUserId){
        this.userId = userId;
        this.followingUserId = followingUserId;
    }
}
