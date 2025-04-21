package com.bot.travel.model.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetLocation {
    private String countryId;
    private String placeName;
}