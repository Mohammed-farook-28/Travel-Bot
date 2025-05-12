package com.bot.travel.model.expense;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "participants")
public class Participant {
    private String userId;
    private Double amountOwed;
    private Boolean isPaid;
    private String paymentLink;
}
