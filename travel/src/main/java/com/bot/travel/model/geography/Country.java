package com.bot.travel.model.geography;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "countries")
public class Country {

    @Id
    private String id;

    @NotBlank(message = "Continent ID is required")
    private String continentId;

    @NotBlank(message = "Country name is required")
    private String name;

    private String flagUrl;
    private String description;
    private String visaLink;
    private String mobileCode;
    
    @NotNull(message = "Language list cannot be null")
    private List<String> languages;

    @NotBlank(message = "Currency is required")
    private String currency;

    private String timezone;
    private String capital;

    private Boolean isDeleted = false;
    private String deletedBy;
    private Instant deletedAt;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
