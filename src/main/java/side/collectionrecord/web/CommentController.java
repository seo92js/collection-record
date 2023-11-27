package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import side.collectionrecord.service.CommentService;
import side.collectionrecord.web.dto.CommentChildForm;
import side.collectionrecord.web.dto.CommentParentForm;

@RequiredArgsConstructor
@Controller
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment/parent")
    public String commentParentAdd(@ModelAttribute CommentParentForm commentParentForm, RedirectAttributes redirectAttributes){
        commentService.commentParentAdd(commentParentForm);

        redirectAttributes.addAttribute("postsId", commentParentForm.getPostsId());
        return "redirect:/posts/{postsId}";
    }

    @PostMapping("/comment/child")
    public String addChildComment(@ModelAttribute CommentChildForm commentChildForm, RedirectAttributes redirectAttributes){
        commentService.commentChildAdd(commentChildForm);

        redirectAttributes.addAttribute("postsId", commentChildForm.getPostsId());
        return "redirect:/posts/{postsId}";
    }
}
