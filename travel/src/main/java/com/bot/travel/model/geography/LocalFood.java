package com.bot.travel.model.geography;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "localfoods")
public class LocalFood {
    private String id;
    private String name;
    private String description;
    private Integer pointsAwarded;
    private String imageUrl;
}