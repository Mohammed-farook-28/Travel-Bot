package com.bot.travel.model.post;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryDay {
    private Integer dayNumber;
    private String activities;
    private String accommodation;
    private String transportationMode;
    private List<String> meals;
}