package side.collectionrecord.domain.follow;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.user.User;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Follow {
    @Id @GeneratedValue
    @Column(name = "follow_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following_id", referencedColumnName = "user_id")
    private User following;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", referencedColumnName = "user_id")
    private User follower;

    public void addFollowing(User user, User followingUser){
        user.following(followingUser, this);
    }

    public void deleteFollowing(User user, User unfollowingUser){
        user.unfollowing(unfollowingUser, this);
    }

    @Builder
    public Follow(User following, User follower){
        this.following = following;
        this.follower = follower;
        //following.getFollowing().add(this);
        //follower.getFollower().add(this);
        addFollowing(following, follower);
    }
}
