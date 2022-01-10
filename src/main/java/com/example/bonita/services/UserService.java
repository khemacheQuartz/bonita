package com.example.bonita.services;

import com.example.bonita.domain.User;
import com.example.bonita.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    public User getUserByLogin(String name) {
        Optional<User> user = userRepository.findByLogin(name);
        return user.get();
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void removeUser(User user) {
        userRepository.delete(user);
    }

}
