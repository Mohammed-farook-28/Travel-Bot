package com.bot.travel.repository.post;

import com.bot.travel.model.post.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByHashtagsContaining(String hashtag);
    List<Post> findByCountryTagsContaining(String countryTag);
}
