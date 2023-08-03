package side.collectionrecord.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.BaseTimeEntity;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.chatmessage.ChatMessage;
import side.collectionrecord.domain.comment.Comment;
import side.collectionrecord.domain.follow.Follow;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.notification.Notification;
import side.collectionrecord.domain.userchatroom.UserChatRoom;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;

    private String profileText;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image profileImage;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "following", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Follow> following = new ArrayList<>();
    @OneToMany(mappedBy = "follower", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Follow> follower = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserChatRoom> userChatRooms = new ArrayList<>();

    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private List<ChatMessage> sendMessage = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<ChatMessage> receiveMessage = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    private List<Notification> sendNotify = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    private List<Notification> receiveNotify = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole;

    public void addCategory(Category category){
        this.categories.add(category);
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public void following(User user, Follow follow){
        this.following.add(follow);
        user.follower.add(follow);
    }

    public void unfollowing(User user, Follow follow){
        this.following.remove(follow);
        user.follower.remove(follow);
    }

    public Follow findFollowByUser(User user){
        for (Follow follow : following) {
            if (follow.getFollower().equals(user)){
                return follow;
            }
        }
        return null;
    }

    public void addUserChatRoom(UserChatRoom userChatRoom){
        this.userChatRooms.add(userChatRoom);
    }

    public void addSendMessage(ChatMessage chatMessage){
        this.sendMessage.add(chatMessage);
    }

    public void addReceiveMessage(ChatMessage chatMessage){
        this.receiveMessage.add(chatMessage);
    }

    public void addSendNotify(Notification notification) {
        this.sendNotify.add(notification);
    }

    public void addReceiveNotify(Notification notification) {
        this.receiveNotify.add(notification);
    }

    @Builder
    public User(String username, String password, Image profileImage, String profileText, UserRole userRole){
        this.username = username;
        this.password = password;
        this.profileImage = profileImage;
        this.profileText = profileText;
        this.userRole = userRole;
    }

    public void update(String username, Image profileImage, String profileText){
        this.username = username;
        this.profileImage = profileImage;
        this.profileText = profileText;
    }
}
