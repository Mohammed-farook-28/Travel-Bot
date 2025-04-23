package com.bot.travel.service.social;

import com.bot.travel.model.social.Follower;
import com.bot.travel.model.social.Friendship;
import com.bot.travel.model.user.User;
import com.bot.travel.repository.social.FollowerRepository;
import com.bot.travel.repository.social.FriendshipRepository;
import com.bot.travel.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SocialService {

    private final FriendshipRepository friendshipRepository;
    private final FollowerRepository followerRepository;
    private final UserRepository userRepository;

    public SocialService(FriendshipRepository friendshipRepository,
                        FollowerRepository followerRepository,
                        UserRepository userRepository) {
        this.friendshipRepository = friendshipRepository;
        this.followerRepository = followerRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Friendship sendFriendRequest(String requesterId, String recipientId) {

        if (!userRepository.existsById(requesterId) || !userRepository.existsById(recipientId)) {
            throw new RuntimeException("One or both users not found");
        }


        Optional<Friendship> existingFriendship = friendshipRepository
            .findByRequesterAndRecipient(requesterId, recipientId);
        
        if (existingFriendship.isPresent()) {
            return existingFriendship.get();
        }


        Friendship friendship = new Friendship();
        friendship.setRequester(requesterId);
        friendship.setRecipient(recipientId);
        friendship.setStatus("PENDING");
        friendship.setCreatedAt(new Date());
        friendship.setUpdatedAt(new Date());
        
        return friendshipRepository.save(friendship);
    }

    @Transactional
    public Friendship acceptFriendRequest(String friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
            .orElseThrow(() -> new RuntimeException("Friendship not found"));
        
        friendship.setStatus("ACCEPTED");
        friendship.setUpdatedAt(new Date());
        
        return friendshipRepository.save(friendship);
    }

    @Transactional
    public Friendship rejectFriendRequest(String friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
            .orElseThrow(() -> new RuntimeException("Friendship not found"));
        
        friendship.setStatus("REJECTED");
        friendship.setUpdatedAt(new Date());
        
        return friendshipRepository.save(friendship);
    }

    @Transactional
    public void removeFriendship(String friendshipId) {
        friendshipRepository.deleteById(friendshipId);
    }

    public List<Friendship> getPendingFriendRequests(String userId) {
        return friendshipRepository.findByRecipientAndStatus(userId, "PENDING");
    }

    public List<Friendship> getSentFriendRequests(String userId) {
        return friendshipRepository.findByRequesterAndStatus(userId, "PENDING");
    }

    public List<User> getFriends(String userId) {

        List<Friendship> friendships = friendshipRepository.findByRequesterAndStatus(userId, "ACCEPTED");
        friendships.addAll(friendshipRepository.findByRecipientAndStatus(userId, "ACCEPTED"));
        

        List<String> friendIds = friendships.stream()
            .map(f -> f.getRequester().equals(userId) ? f.getRecipient() : f.getRequester())
            .collect(Collectors.toList());
        

        return userRepository.findAllById(friendIds);
    }


    @Transactional
    public Follower followUser(String followerId, String followingId) {

        if (!userRepository.existsById(followerId) || !userRepository.existsById(followingId)) {
            throw new RuntimeException("One or both users not found");
        }

        Optional<Follower> existingFollower = followerRepository
            .findByFollowerIdAndFollowingId(followerId, followingId);
        
        if (existingFollower.isPresent()) {
            return existingFollower.get();
        }


        Follower follower = new Follower();
        follower.setFollowerId(followerId);
        follower.setFollowingId(followingId);
        follower.setCreatedAt(new Date());
        
        return followerRepository.save(follower);
    }

    @Transactional
    public void unfollowUser(String followerId, String followingId) {
        followerRepository.deleteByFollowerIdAndFollowingId(followerId, followingId);
    }

    public List<User> getFollowers(String userId) {
        List<Follower> followers = followerRepository.findByFollowingId(userId);
        List<String> followerIds = followers.stream()
            .map(Follower::getFollowerId)
            .collect(Collectors.toList());
        
        return userRepository.findAllById(followerIds);
    }

    public List<User> getFollowing(String userId) {
        List<Follower> following = followerRepository.findByFollowerId(userId);
        List<String> followingIds = following.stream()
            .map(Follower::getFollowingId)
            .collect(Collectors.toList());
        
        return userRepository.findAllById(followingIds);
    }

    public boolean isFollowing(String followerId, String followingId) {
        return followerRepository.existsByFollowerIdAndFollowingId(followerId, followingId);
    }
}