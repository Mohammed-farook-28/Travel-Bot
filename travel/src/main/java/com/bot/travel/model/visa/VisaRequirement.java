package com.bot.travel.model.visa;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "visa_requirements")
public class VisaRequirement {
    @Id
    private String id;
    private String passportCountry;
    private String destinationCountry;
    private VisaType requirementType;
    private Integer maxStayDays;
    private String notes;
    private Date updatedAt;
}