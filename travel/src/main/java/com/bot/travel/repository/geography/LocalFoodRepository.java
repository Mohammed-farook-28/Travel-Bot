package com.bot.travel.repository.geography;

import com.bot.travel.model.geography.LocalFood;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LocalFoodRepository extends MongoRepository<LocalFood, String> {
    List<LocalFood> findByPointsAwardedGreaterThan(Integer points);
    List<LocalFood> findByNameContaining(String name);
}