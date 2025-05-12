// com.bot.travel.repository.user.UserRepository.java
package com.bot.travel.repository.user;

import com.bot.travel.model.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsernameAndIsDeletedFalse(String username);
    Optional<User> findByEmailAndIsDeletedFalse(String email);
    List<User> findByUsernameContainingIgnoreCaseAndIsDeletedFalse(String username);
    List<User> findAllByIsDeletedFalse();
}
