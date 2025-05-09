package com.bot.travel.controller.post;

import com.bot.travel.model.post.Post;
import com.bot.travel.service.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.createPost(post));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable String id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable String id, @RequestBody Post post) {
        return ResponseEntity.ok(postService.updatePost(id, post));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/hashtag")
    public ResponseEntity<List<Post>> searchByHashtag(@RequestParam String hashtag) {
        return ResponseEntity.ok(postService.getPostsByHashtag(hashtag));
    }

    @GetMapping("/search/country")
    public ResponseEntity<List<Post>> searchByCountryTag(@RequestParam String countryTag) {
        return ResponseEntity.ok(postService.getPostsByCountryTag(countryTag));
    }
}
