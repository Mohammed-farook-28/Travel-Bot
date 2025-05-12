// com.bot.travel.service.audit.AuditLoggerService.java
package com.bot.travel.service.audit;

import com.bot.travel.model.audit.AuditLog;
import com.bot.travel.repository.audit.AuditLogRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
public class AuditLoggerService {

    private final AuditLogRepository auditLogRepository;

    public AuditLoggerService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public void logEvent(String modelName, String operation, String userId, Map<String, Object> changes, String performedBy) {
        AuditLog log = AuditLog.builder()
                .modelName(modelName)
                .operation(operation)
                .userId(userId)
                .changes(changes)
                .performedBy(performedBy)
                .timestamp(Instant.now())
                .build();
        auditLogRepository.save(log);
    }
}
