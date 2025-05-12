package com.bot.travel.service.social;

import com.bot.travel.model.social.Follower;
import com.bot.travel.repository.social.FollowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowerService {

    @Autowired
    private final FollowerRepository followerRepository;

    @Transactional(readOnly = true)
    public List<Follower> getAllFollowers() {
        return followerRepository.findAllByIsDeletedFalse();
    }

    @Transactional(readOnly = true)
    public Optional<Follower> getFollower(String followerId, String followingId) {
        return followerRepository.findByFollowerIdAndFollowingIdAndIsDeletedFalse(followerId, followingId);
    }

    @Transactional
    public Follower createFollower(Follower follower) {
        follower.setCreatedAt(Instant.now());

        Optional<Follower> reverseFollower = followerRepository.findByFollowerIdAndFollowingIdAndIsDeletedFalse(
                follower.getFollowingId(), follower.getFollowerId()
        );

        if (reverseFollower.isPresent()) {
            follower.setIsMutual(true);
            reverseFollower.get().setIsMutual(true);
            reverseFollower.get().setMutualSince(Instant.now());
            follower.setMutualSince(Instant.now());
            followerRepository.save(reverseFollower.get());
        }

        return followerRepository.save(follower);
    }

    @Transactional
    public void deleteFollower(String id, String deletedBy) {
        followerRepository.findById(id).ifPresent(follower -> {
            follower.setIsDeleted(true);
            follower.setDeletedBy(deletedBy);
            follower.setDeletedAt(Instant.now());
            followerRepository.save(follower);

            // Remove mutual flag if present
            followerRepository.findByFollowerIdAndFollowingIdAndIsDeletedFalse(
                follower.getFollowingId(), follower.getFollowerId()
            ).ifPresent(reverseFollower -> {
                reverseFollower.setIsMutual(false);
                reverseFollower.setMutualSince(null);
                followerRepository.save(reverseFollower);
            });
        });
    }
}
