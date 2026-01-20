package com.movie.dea.service;

import com.movie.dea.entity.User;
import com.movie.dea.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User add(@RequestBody User user) {
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(@PathVariable Integer user_id) {
        return userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("No user with user_id" + user_id));
    }

    public User update(@PathVariable Integer user_id, @RequestBody User updatedUser) {
        return userRepository.findById(user_id)
                .map(existing -> {
                    existing.setUsername(updatedUser.getUsername());
                    existing.setPassword(updatedUser.getPassword());
                    existing.setPhone_number(updatedUser.getPhone_number());
                    existing.setAge(updatedUser.getAge());
                    return userRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("No user with id" + user_id));
    }

    public void delete(@PathVariable Integer user_id) {
        userRepository.deleteById(user_id);
    }
}
