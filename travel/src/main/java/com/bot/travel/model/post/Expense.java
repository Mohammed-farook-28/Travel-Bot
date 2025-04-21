package com.bot.travel.model.post;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    private String category;
    private Double amount;
    private String currency;
}