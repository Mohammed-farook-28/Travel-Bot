// com.bot.travel.model.user.TriedLocalFood.java
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
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.URL;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "tried_local_foods")
public class TriedLocalFood {
    @Id
    private String id;

    @Indexed
    @NotBlank
    private String userId;

    @Indexed
    @NotBlank
    private String countryId;

    @NotBlank
    private String foodId;

    @URL
    private String proofImageUrl;

    @Min(0)
    private Integer pointsAwarded;

    private Boolean isDeleted = false;
    private String deletedBy;
    private Instant deletedAt;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
