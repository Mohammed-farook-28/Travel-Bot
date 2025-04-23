package com.bot.travel.model.social;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String status;
    private Date createdAt;
    private Date updatedAt;
}