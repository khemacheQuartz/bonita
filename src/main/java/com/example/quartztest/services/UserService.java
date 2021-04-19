package com.example.quartztest.services;

import com.example.quartztest.domain.Game;
import com.example.quartztest.domain.User;
import com.example.quartztest.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void removeUser(User user) {
        userRepository.delete(user);
    }

    public Set<User> getAllFriendsByUser(User user) {
        return user.getFriends();
    }

    public Set<Game> getAllGamesByUser(User user) {
        return user.getGames();
    }

}
