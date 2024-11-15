package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String handleHelle() {
        return "hello from service";
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public List<User> getAllUsersByEamil(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User getUserByID(long id){
        return this.userRepository.findById(id);
    }

    public User handSaveUser(User user) {
        // return this.userRepository.save(user);
        User eric = this.userRepository.save(user);
        System.out.println(eric);
        return eric;
    }
}
