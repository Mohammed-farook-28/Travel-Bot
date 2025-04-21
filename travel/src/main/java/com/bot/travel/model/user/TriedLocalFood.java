package com.bot.travel.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TriedLocalFood {
    private String countryId;
    private String foodId;
    private Date triedDate;
    private String proofImageUrl;
    private String review;
    private Integer pointsAwarded;
}