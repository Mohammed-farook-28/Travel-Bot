package com.bot.travel.model.post;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Itinerary {
    private String title;
    private Integer durationDays;
    private Double totalExpense;
    private String currency;
    private TravelType travelType;
    private TravelStyle travelStyle;
    private Difficulty difficulty;
    private List<String> bestTimeToVisit;
    private List<ItineraryDay> days;
    private List<Expense> expenses;
    private String tips;
    private String warnings;
    private List<String> highlights;
}