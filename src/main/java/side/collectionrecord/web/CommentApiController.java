package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import side.collectionrecord.service.CommentService;
import side.collectionrecord.web.dto.CreateChildCommentRequestDto;
import side.collectionrecord.web.dto.CreateParentCommentRequestDto;

@RequiredArgsConstructor
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping("/api/v1/parent-comment")
    public Long createParentComment(@RequestBody CreateParentCommentRequestDto createParentCommentRequestDto){
        Long id = commentService.createParentComment(createParentCommentRequestDto);

        return id;
    }

    @PostMapping("/api/v1/child-comment")
    public Long createChildComment(@RequestBody CreateChildCommentRequestDto createChildCommentRequestDto){
        Long id = commentService.createChildComment(createChildCommentRequestDto);

        return id;
    }

    @DeleteMapping("/api/v1/comment/{id}")
    public Long deleteComment(@PathVariable Long id){
        commentService.deleteComment(id);

        return id;
    }
}
