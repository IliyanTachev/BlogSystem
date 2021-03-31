package core.blog.system.init;

import core.blog.system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseInit {
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void seed(){
        System.out.println("Broi = " + this.userRepository.count());
    }
}
