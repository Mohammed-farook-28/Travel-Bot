package com.bot.travel.repository.post;

import com.bot.travel.model.post.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByUserId(String userId);
    List<Comment> findByCreatedAtBetween(Date startDate, Date endDate);
}