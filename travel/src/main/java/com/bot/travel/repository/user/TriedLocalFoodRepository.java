// com.bot.travel.repository.user.TriedLocalFoodRepository.java
package com.bot.travel.repository.user;

import com.bot.travel.model.user.TriedLocalFood;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TriedLocalFoodRepository extends MongoRepository<TriedLocalFood, String> {
    List<TriedLocalFood> findByUserIdAndIsDeletedFalse(String userId);
    Optional<TriedLocalFood> findByUserIdAndFoodIdAndIsDeletedFalse(String userId, String foodId);
    List<TriedLocalFood> findAllByIsDeletedFalse();
}
