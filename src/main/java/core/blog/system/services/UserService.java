package core.blog.system.services;

import core.blog.system.models.binding.UserPostsModel;
import core.blog.system.models.binding.UserRegisterModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void register(UserRegisterModel user);
    UserPostsModel getUserWithPostsByUsername(String username);
}
