package com.bot.travel.repository.social;

import com.bot.travel.model.social.Follower;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowerRepository extends MongoRepository<Follower, String> {
    Optional<Follower> findByFollowerIdAndFollowingId(String followerId, String followingId);
    void deleteByFollowerIdAndFollowingId(String followerId, String followingId);
    List<Follower> findByFollowerId(String followerId);
    List<Follower> findByFollowingId(String followingId);
    boolean existsByFollowerIdAndFollowingId(String followerId, String followingId);
}