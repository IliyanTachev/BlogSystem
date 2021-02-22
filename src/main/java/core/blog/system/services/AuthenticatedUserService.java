package core.blog.system.services;

import core.blog.system.entities.User;
import core.blog.system.repositories.UserRepository;
import core.blog.system.entities.AuthenticatedUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthenticatedUserService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) throw new UsernameNotFoundException("Username was not found.");
        else return new AuthenticatedUser(user);
    }
}
