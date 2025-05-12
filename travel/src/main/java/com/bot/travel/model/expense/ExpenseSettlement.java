package com.bot.travel.model.expense;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseSettlement {
    private String payerId; 
    private String receiverId;
    private Double amount; 
    private Boolean isSettled;
    private String paymentLink;
}
