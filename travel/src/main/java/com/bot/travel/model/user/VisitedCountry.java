// com.bot.travel.model.user.VisitedCountry.java
package com.bot.travel.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "visited_countries")
public class VisitedCountry {
    @Id
    private String id;

    @Indexed
    @NotBlank
    private String userId;

    @Indexed
    @NotBlank
    private String countryId;

    private Boolean isDeleted = false;
    private String deletedBy;
    private Instant deletedAt;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
