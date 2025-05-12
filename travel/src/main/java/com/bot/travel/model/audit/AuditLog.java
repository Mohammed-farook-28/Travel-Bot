// com.bot.travel.model.audit.AuditLog.java
package com.bot.travel.model.audit;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "audit_logs")
public class AuditLog {
    @Id
    private String id;

    @Indexed
    private String modelName;

    @Indexed
    private String operation;

    @Indexed
    private String userId;

    private Map<String, Object> changes;

    private String performedBy;

    @CreatedDate
    private Instant timestamp;
}
