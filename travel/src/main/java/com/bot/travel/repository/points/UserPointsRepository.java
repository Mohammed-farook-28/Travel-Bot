package com.bot.travel.repository.points;

import com.bot.travel.model.points.UserPoints;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface UserPointsRepository extends MongoRepository<UserPoints, String> {
    Optional<UserPoints> findByUserId(String userId);
    List<UserPoints> findByTotalPointsBetween(Integer minPoints, Integer maxPoints);
    List<UserPoints> findByTotalPointsGreaterThan(Integer points);
    List<UserPoints> findByOrderByTotalPointsDesc();
}