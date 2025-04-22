package com.bot.travel.model.expense;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    private String userId;
    private Double amountOwed;
    private Boolean isPaid;
    private String paymentLink;
}
