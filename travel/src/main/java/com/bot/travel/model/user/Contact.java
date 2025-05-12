// com.bot.travel.model.user.Contact.java
package com.bot.travel.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "contacts")
public class Contact {
    @Id
    private String id;

    @Indexed
    @NotBlank
    private String userId;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    private String nationality;

    @URL
    private String profilePicture;

    @NotNull
    private MeetLocation meetLocation;

    @NotNull
    private Instant meetDate;

    @Builder.Default
    private List<SocialLink> socialLinks = new ArrayList<>();

    private String notes;

    private Boolean isDeleted = false;
    private String deletedBy;
    private Instant deletedAt;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
