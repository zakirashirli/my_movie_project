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

    public User getUserById(@PathVariable Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No user with id" + id));
    }

    public User update(@PathVariable Integer id, @RequestBody User updatedUser) {
        return userRepository.findById(id)
                .map(existing -> {
                    existing.setUsername(updatedUser.getUsername());
                    existing.setPassword(updatedUser.getPassword());
                    existing.setPhone_number(updatedUser.getPhone_number());
                    existing.setAge(updatedUser.getAge());
                    return userRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("No user with id" + id));
    }

    public void delete(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }
}
