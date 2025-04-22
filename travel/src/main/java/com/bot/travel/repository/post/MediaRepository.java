package com.bot.travel.repository.post;

import com.bot.travel.model.post.Media;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MediaRepository extends MongoRepository<Media, String> {
    List<Media> findByMediaType(String mediaType);
}