package com.bot.travel.model.points;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_points")
public class UserPoints {
    @Id
    private String id;
    private String userId;
    private Integer totalPoints;
    private List<PointHistory> pointsHistory;
}