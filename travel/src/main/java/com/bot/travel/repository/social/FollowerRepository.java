package com.bot.travel.repository.social;

import com.bot.travel.model.social.Follower;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FollowerRepository extends MongoRepository<Follower, String> {
    // Find followers of a user
    List<Follower> findByFollowingId(String followingId);

    // Find users a specific user is following
    List<Follower> findByFollowerId(String followerId);

    // Check if a follow relationship exists
    boolean existsByFollowerIdAndFollowingId(String followerId, String followingId);

    // Count followers and following
    Long countByFollowingId(String followingId);
    Long countByFollowerId(String followerId);

    // Remove a specific follow relationship
    void deleteByFollowerIdAndFollowingId(String followerId, String followingId);
}