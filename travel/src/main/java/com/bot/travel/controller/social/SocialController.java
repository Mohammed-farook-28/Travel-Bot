package com.bot.travel.controller.social;

import com.bot.travel.model.social.Follower;
import com.bot.travel.model.social.Friendship;
import com.bot.travel.model.user.User;
import com.bot.travel.service.social.SocialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SocialController {

    private final SocialService socialService;

    public SocialController(SocialService socialService) {
        this.socialService = socialService;
    }

    @PostMapping("/friendships/request/{recipientId}")
    public ResponseEntity<Friendship> sendFriendRequest(
            @RequestAttribute("userId") String requesterId,
            @PathVariable String recipientId) {
        try {
            Friendship friendship = socialService.sendFriendRequest(requesterId, recipientId);
            return new ResponseEntity<>(friendship, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/friendships/{id}/accept")
    public ResponseEntity<Friendship> acceptFriendRequest(@PathVariable String id) {
        try {
            return ResponseEntity.ok(socialService.acceptFriendRequest(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/friendships/{id}/reject")
    public ResponseEntity<Friendship> rejectFriendRequest(@PathVariable String id) {
        try {
            return ResponseEntity.ok(socialService.rejectFriendRequest(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/friendships/{id}")
    public ResponseEntity<Void> removeFriendship(@PathVariable String id) {
        try {
            socialService.removeFriendship(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/users/{id}/friend-requests/pending")
    public ResponseEntity<List<Friendship>> getPendingFriendRequests(@PathVariable String id) {
        return ResponseEntity.ok(socialService.getPendingFriendRequests(id));
    }

    @GetMapping("/users/{id}/friend-requests/sent")
    public ResponseEntity<List<Friendship>> getSentFriendRequests(@PathVariable String id) {
        return ResponseEntity.ok(socialService.getSentFriendRequests(id));
    }

    @GetMapping("/users/{id}/friends")
    public ResponseEntity<List<User>> getFriends(@PathVariable String id) {
        return ResponseEntity.ok(socialService.getFriends(id));
    }

    @PostMapping("/follow/{followingId}")
    public ResponseEntity<Follower> followUser(
            @RequestAttribute("userId") String followerId,
            @PathVariable String followingId) {
        try {
            Follower follower = socialService.followUser(followerId, followingId);
            return new ResponseEntity<>(follower, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/follow/{followingId}")
    public ResponseEntity<Void> unfollowUser(
            @RequestAttribute("userId") String followerId,
            @PathVariable String followingId) {
        socialService.unfollowUser(followerId, followingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/{id}/followers")
    public ResponseEntity<List<User>> getFollowers(@PathVariable String id) {
        return ResponseEntity.ok(socialService.getFollowers(id));
    }

    @GetMapping("/users/{id}/following")
    public ResponseEntity<List<User>> getFollowing(@PathVariable String id) {
        return ResponseEntity.ok(socialService.getFollowing(id));
    }

    @GetMapping("/users/{followerId}/is-following/{followingId}")
    public ResponseEntity<Boolean> isFollowing(
            @PathVariable String followerId,
            @PathVariable String followingId) {
        return ResponseEntity.ok(socialService.isFollowing(followerId, followingId));
    }
}