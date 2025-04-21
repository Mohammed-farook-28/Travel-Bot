package com.bot.travel.model.geography;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "countries")
public class Country {
    @Id
    private String id;
    private String continentId;
    private String name;
    private String flagUrl;
    private String description;
    private String visaLink;
    private String mobileCode;
    private List<String> languages;
    private String currency;
    private String timezone;
    private String capital;
    
    private List<BucketListItem> bucketListItems;
    private List<LocalFood> localFoods;
    
    private Integer totalBucketListPoints;
    private Integer totalFoodPoints;
}