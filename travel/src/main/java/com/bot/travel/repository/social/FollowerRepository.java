package com.bot.travel.repository.social;

import com.bot.travel.model.social.Follower;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowerRepository extends MongoRepository<Follower, String> {
    Optional<Follower> findByFollowerIdAndFollowingIdAndIsDeletedFalse(String followerId, String followingId);
    List<Follower> findAllByFollowerIdAndIsDeletedFalse(String followerId);
    List<Follower> findAllByFollowingIdAndIsDeletedFalse(String followingId);
    List<Follower> findAllByIsDeletedFalse();
}
