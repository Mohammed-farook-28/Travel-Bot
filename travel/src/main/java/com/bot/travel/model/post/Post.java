package com.bot.travel.model.post;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    private String userId;
    private String content;
    private List<Media> media;
    private List<String> countryTags;
    private List<String> hashtags;
    private Integer likeCount;
    private Integer commentCount;
    private List<Comment> comments;
    private Itinerary itinerary;
    private Date createdAt;
    private Date updatedAt;
}
