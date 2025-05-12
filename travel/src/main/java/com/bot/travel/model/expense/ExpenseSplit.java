package com.bot.travel.model.expense;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "expensesplits")
public class ExpenseSplit {

    @Id
    private String id;
    private String chatRoomId;
    private String spenderId;
    private Double amount;
    private String description;
    private List<String> participants;
}
