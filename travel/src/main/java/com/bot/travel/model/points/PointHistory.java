package com.bot.travel.model.points;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "pointHistory")
public class PointHistory {
    @Id
    private String id;
    private String userId;
    private String actionType;
    private int points;
    private String countryId;
    private String itemId;
    private Date timestamp;
}