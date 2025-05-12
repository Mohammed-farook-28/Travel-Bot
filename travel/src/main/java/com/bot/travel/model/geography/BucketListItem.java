package com.bot.travel.model.geography;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bucketlistitems")  // or ""
public class BucketListItem {
    private String id;
    private String name;
    private String description;
    private String location;
    private Integer pointsAwarded;
    private String imageUrl;
}