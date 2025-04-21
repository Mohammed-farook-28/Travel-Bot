package com.bot.travel.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bot.travel.model.common.MeetLocation;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "contacts")
public class Contact {
    @Id
    private String id;
    private String userId;
    private String name;
    private String nationality;
    private String profilePicture;
    
    private MeetLocation meetLocation;
    private Date meetDate;
    private List<SocialLink> socialLinks;
    private String notes;
    
    private Date createdAt;
    private Date updatedAt;
}