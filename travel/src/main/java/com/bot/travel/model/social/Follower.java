package com.bot.travel.model.social;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "followers")
public class Follower {

    @Id
    private String id;

    @NotBlank(message = "Follower ID is required")
    private String followerId;

    @NotBlank(message = "Following ID is required")
    private String followingId;

    private Boolean isMutual = false;
    private Instant mutualSince;

    private Boolean isDeleted = false;
    private String deletedBy;
    private Instant deletedAt;
    
    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
