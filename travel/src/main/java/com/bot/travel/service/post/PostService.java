package com.bot.travel.service.post;

import com.bot.travel.model.post.Post;
import com.bot.travel.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post createPost(Post post) {
        post.setLikeCount(0);
        post.setCommentCount(0);
        post.setCreatedAt(new Date());
        post.setUpdatedAt(new Date());
        return postRepository.save(post);
    }

    public Post getPostById(String id) {
        return postRepository.findById(id).orElse(null);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPostsByHashtag(String hashtag) {
        return postRepository.findByHashtagsContaining(hashtag);
    }

    public List<Post> getPostsByCountryTag(String countryTag) {
        return postRepository.findByCountryTagsContaining(countryTag);
    }

    public Post updatePost(String id, Post post) {
        post.setId(id);
        post.setUpdatedAt(new Date());
        return postRepository.save(post);
    }

    public void deletePost(String id) {
        postRepository.deleteById(id);
    }
}
