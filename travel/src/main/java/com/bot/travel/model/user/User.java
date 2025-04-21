package com.bot.travel.model.user;

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
@Document(collection = "users")
public class User {
    @Id
    private String id;
    
    private String username;
    private String email;
    private String passwordHash;
    private Date dateOfBirth;
    private String nationality;
    private String bio;
    private String profilePictureUrl;
    
    private List<SocialLink> socialLinks;
    private List<Passport> passports;
    private List<VisitedCountry> visitedCountries;
    private List<CompletedBucketListItem> completedBucketListItems;
    private List<TriedLocalFood> triedLocalFoods;
    
    private Integer totalPoints;
    private Date createdAt;
    private Date updatedAt;
}