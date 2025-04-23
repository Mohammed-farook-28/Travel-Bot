package com.bot.travel.repository.points;

import com.bot.travel.model.points.UserPoints;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPointsRepository extends MongoRepository<UserPoints, String> {
    Optional<UserPoints> findByUserId(String userId);
    List<UserPoints> findByOrderByTotalPointsDesc(Pageable pageable);
    List<UserPoints> findByTotalPointsGreaterThan(int points);
}