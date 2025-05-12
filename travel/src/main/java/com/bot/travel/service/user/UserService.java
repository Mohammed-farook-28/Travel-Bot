// com.bot.travel.service.user.UserService.java
package com.bot.travel.service.user;

import com.bot.travel.model.user.User;
import com.bot.travel.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAllByIsDeletedFalse();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id)
                .filter(user -> !user.getIsDeleted());
    }

    @Transactional
    public User saveUser(@Valid User user) {
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(String id, @Valid User user) {
        return userRepository.findById(id)
            .map(existingUser -> {
                user.setId(id);
                user.setUpdatedAt(Instant.now());
                return userRepository.save(user);
            })
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Transactional
    public void softDeleteUser(String id, String deletedBy) {
        userRepository.findById(id).ifPresent(user -> {
            user.setIsDeleted(true);
            user.setDeletedBy(deletedBy);
            user.setDeletedAt(Instant.now());
            userRepository.save(user);
        });
    }

    @Transactional
    public void restoreUser(String id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setIsDeleted(false);
            user.setDeletedBy(null);
            user.setDeletedAt(null);
            userRepository.save(user);
        });
    }
}
