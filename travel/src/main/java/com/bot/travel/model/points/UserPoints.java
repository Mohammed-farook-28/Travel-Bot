package com.bot.travel.model.points;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "userPoints")
public class UserPoints {
    @Id
    private String id;
    private String userId;
    private int totalPoints;
    
    // Constructor used in service
    public UserPoints(String userId, int initialPoints) {
        this.userId = userId;
        this.totalPoints = initialPoints;
    }
}