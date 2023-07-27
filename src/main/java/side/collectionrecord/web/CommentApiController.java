package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import side.collectionrecord.service.CommentService;
import side.collectionrecord.web.dto.CommentAddRequestDto;
import side.collectionrecord.web.dto.CommentChildAddRequestDto;

@RequiredArgsConstructor
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/api/v1/comment-add")
    public Long save(@RequestBody CommentAddRequestDto commentAddRequestDto){
        Long id = commentService.addComment(commentAddRequestDto);

        return id;
    }

    @PostMapping("/api/v1/comment-child-add")
    public Long childSave(@RequestBody CommentChildAddRequestDto commentChildAddRequestDto){
        Long id = commentService.addCommentChild(commentChildAddRequestDto);

        return id;
    }

    @DeleteMapping("/api/v1/comment-delete/{id}")
    public Long delete(@PathVariable Long id){
        commentService.deleteComment(id);

        return id;
    }
}
