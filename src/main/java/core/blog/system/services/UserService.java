package core.blog.system.services;

import core.blog.system.entities.User;
import core.blog.system.models.binding.UserModel;
import core.blog.system.models.binding.UserPostsModel;
import core.blog.system.models.binding.UserRegisterModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void register(UserRegisterModel user);
    UserPostsModel getUserWithPostsByUsername(String username);
    User getUserByUsername(String username);
    void save(User user);
}
