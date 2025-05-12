// com.bot.travel.service.user.UserService.java
package com.bot.travel.service.user;

import com.bot.travel.model.user.User;
import com.bot.travel.repository.user.UserRepository;
import com.bot.travel.service.audit.AuditLoggerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuditLoggerService auditLoggerService;

    public UserService(UserRepository userRepository, AuditLoggerService auditLoggerService) {
        this.userRepository = userRepository;
        this.auditLoggerService = auditLoggerService;
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
        User savedUser = userRepository.save(user);

        Map<String, Object> changes = new HashMap<>();
        changes.put("username", user.getUsername());
        changes.put("email", user.getEmail());
        auditLoggerService.logEvent("User", "CREATE", user.getId(), changes, "SYSTEM");

        return savedUser;
    }

    @Transactional
    public User updateUser(String id, @Valid User user) {
        return userRepository.findById(id)
            .map(existingUser -> {
                user.setId(id);
                user.setUpdatedAt(Instant.now());
                User updatedUser = userRepository.save(user);

                Map<String, Object> changes = new HashMap<>();
                changes.put("username", user.getUsername());
                changes.put("email", user.getEmail());
                auditLoggerService.logEvent("User", "UPDATE", user.getId(), changes, "SYSTEM");

                return updatedUser;
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

            Map<String, Object> changes = new HashMap<>();
            changes.put("isDeleted", true);
            auditLoggerService.logEvent("User", "DELETE", user.getId(), changes, deletedBy);
        });
    }

    @Transactional
    public void restoreUser(String id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setIsDeleted(false);
            user.setDeletedBy(null);
            user.setDeletedAt(null);
            userRepository.save(user);

            Map<String, Object> changes = new HashMap<>();
            changes.put("isDeleted", false);
            auditLoggerService.logEvent("User", "RESTORE", user.getId(), changes, "SYSTEM");
        });
    }
}
