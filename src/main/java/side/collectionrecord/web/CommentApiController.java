package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import side.collectionrecord.service.CommentService;

@RequiredArgsConstructor
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @DeleteMapping("/api/v1/comment/{id}")
    public Long deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);

        return id;
    }
}
