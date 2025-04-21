package com.bot.travel.model.social;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "friendships")
public class Friendship {
    @Id
    private String id;
    private String requester;
    private String recipient;
    private FriendshipStatus status;
    private Date createdAt;
    private Date updatedAt;
}