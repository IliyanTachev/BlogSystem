package core.blog.system.controllers;

import core.blog.system.models.binding.UserPostsModel;
import core.blog.system.models.binding.UserRegisterModel;
import core.blog.system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String viewHomePage(Model model)
    {
        return "home";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegisterModel());
        return "signup_form";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login_form";
    }

    @PostMapping("/register")
    public String processRegister(UserRegisterModel user, Model model){
        // Error handling for passwords mismatch
        try{
            userService.register(user);
        } catch(Exception ex){
            ex.printStackTrace();
        }
        model.addAttribute("register_status", "success");
        return "login_form";
    }

    @GetMapping("/profile/{username}")
    public String showUserProfile(@PathVariable String username, Model model){
        UserPostsModel userWithPosts = this.userService.getUserWithPostsByUsername(username);
        model.addAttribute("profile_username", username);
        model.addAttribute("posts", userWithPosts.getPosts());
        return "profile";
    }
}
