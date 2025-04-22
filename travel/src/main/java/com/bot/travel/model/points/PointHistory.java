package com.bot.travel.model.points;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointHistory {
    private PointActionType actionType;
    private Integer points;
    private String countryId;
    private String itemId;
    private Date timestamp;
}