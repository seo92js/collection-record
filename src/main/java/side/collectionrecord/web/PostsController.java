package side.collectionrecord.web;

import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.service.CategoryService;
import side.collectionrecord.service.PostsService;
import side.collectionrecord.web.dto.CategoryListResponseDto;
import side.collectionrecord.web.dto.PostsAddRequestDto;
import side.collectionrecord.web.dto.PostsResponseDto;
import side.collectionrecord.web.dto.PostsUpdateRequestDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PostsController {
    @Autowired
    PostsService postsService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/posts/add")
    public String posts(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();

        Long userId = (Long)session.getAttribute("userId");

        List<CategoryListResponseDto> categories = categoryService.findCategories(userId);

        model.addAttribute("postsAddRequestDto", PostsAddRequestDto.builder()
                        .userId(userId)
                        .categoryName(null)
                        .title(null)
                        .image(null)
                        .text(null)
                        .build());

        model.addAttribute("categories", categories);

        return "posts/postsAdd";
    }

    @GetMapping("/posts/{id}")
    public String postsView(@PathVariable Long id, Model model){

        Posts posts = postsService.findPosts(id);

        model.addAttribute("postsResponseDto", new PostsResponseDto(posts));

        return "posts/posts";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){

        Posts posts = postsService.findPosts(id);

        model.addAttribute(PostsUpdateRequestDto.builder()
                        .categoryName(posts.getCategory().getName())
                        .title(null)
                        .text(null)
                        .image(null)
                        .build());

        return "posts/postsUpdate";
    }
}
