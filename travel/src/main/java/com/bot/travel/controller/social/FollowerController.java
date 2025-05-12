package com.bot.travel.controller.social;

import com.bot.travel.model.social.Follower;
import com.bot.travel.service.social.FollowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/followers")
@RequiredArgsConstructor
public class FollowerController {

    private final FollowerService followerService;

    @GetMapping
    public ResponseEntity<List<Follower>> getAllFollowers() {
        List<Follower> followers = followerService.getAllFollowers();
        return ResponseEntity.ok(followers);
    }

    @GetMapping("/{followerId}/{followingId}")
    public ResponseEntity<Follower> getFollower(@PathVariable String followerId, @PathVariable String followingId) {
        Optional<Follower> follower = followerService.getFollower(followerId, followingId);
        return follower.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/mutual/{userId}")
    public ResponseEntity<List<Follower>> getMutualFollowers(@PathVariable String userId) {
        List<Follower> mutualFollowers = followerService.getAllFollowers().stream()
                .filter(follower -> follower.getFollowerId().equals(userId) && follower.getIsMutual())
                .toList();
        return ResponseEntity.ok(mutualFollowers);
    }

    @PostMapping
    public ResponseEntity<Follower> createFollower(@RequestBody Follower follower) {
        Follower created = followerService.createFollower(follower);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFollower(@PathVariable String id, @RequestParam String deletedBy) {
        followerService.deleteFollower(id, deletedBy);
        return ResponseEntity.noContent().build();
    }
}
