package com.bot.travel.repository.post;

import com.bot.travel.model.post.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByUserId(String userId);
    List<Post> findByCountryTagsContaining(String countryId);
    List<Post> findByCreatedAtBetween(Date startDate, Date endDate);
    List<Post> findByHashtagsContaining(String hashtag);
    List<Post> findByItinerary_TravelType(String travelType);
}