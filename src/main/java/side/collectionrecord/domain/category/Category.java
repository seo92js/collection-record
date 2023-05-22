package side.collectionrecord.domain.category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String name;
}
