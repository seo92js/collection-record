package side.collectionrecord.config.auth.dto;

import lombok.Getter;
import side.collectionrecord.domain.user.User;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;

    public SessionUser(User user){
        this.name = user.getUsername();
        this.email = user.getEmail();
    }
}
