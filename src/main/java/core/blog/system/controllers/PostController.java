package core.blog.system.controllers;

import core.blog.system.models.binding.PostModel;
import core.blog.system.repositories.PostRepository;
import core.blog.system.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(@ModelAttribute PostModel post){
        postService.save(post);
        return "success";
    }

}
