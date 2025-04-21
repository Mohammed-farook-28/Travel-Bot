package com.bot.travel.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompletedBucketListItem {
    private String countryId;
    private String bucketListItemId;
    private Date completionDate;
    private String proofImageUrl;
    private String description;
    private Boolean approved;
    private Integer pointsAwarded;
}