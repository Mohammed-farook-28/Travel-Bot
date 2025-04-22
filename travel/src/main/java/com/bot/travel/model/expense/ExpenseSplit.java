package com.bot.travel.model.expense;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "expense_splits")
public class ExpenseSplit {
    @Id
    private String id;
    private String title;
    private Double totalAmount;
    private String currency;
    private String createdBy;
    private List<Participant> participants;
    private Date createdAt;
    private Date updatedAt;
}
