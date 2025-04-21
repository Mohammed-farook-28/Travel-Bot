package com.bot.travel.model.geography;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "continents")
public class Continent {
    @Id
    private String id;
    private String name;
    private String description;
    private String imageUrl;
}