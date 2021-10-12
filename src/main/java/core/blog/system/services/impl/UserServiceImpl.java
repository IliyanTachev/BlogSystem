package core.blog.system.services.impl;

import core.blog.system.entities.User;
import core.blog.system.entities.UserRole;
import core.blog.system.models.binding.UserPostsModel;
import core.blog.system.models.binding.UserRegisterModel;
import core.blog.system.repositories.UserRepository;
import core.blog.system.entities.AuthenticatedUser;
import core.blog.system.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) throw new UsernameNotFoundException("Username was not found.");
        else return new AuthenticatedUser(user);
    }

    @Override
    public void register(UserRegisterModel user) {
        if(!user.passwordsMatch()) throw new IllegalStateException("Passwords does not match.");
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRoles(getRegistrationRoles());
        userRepository.save(modelMapper.map(user, User.class));
    }

    public UserPostsModel getUserWithPostsByUsername(String username){
        return modelMapper.map(this.getUserByUsername(username), UserPostsModel.class);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public void save(User user){
        this.userRepository.save(user);
    }

    private Set<UserRole> getRegistrationRoles(){
        Set<UserRole> roles = new HashSet<>();
        if(this.userRepository.count() == 0) roles.add(new UserRole("ROOT_ADMIN"));
        else roles.add(new UserRole("USER"));

        return roles;
    }
}
