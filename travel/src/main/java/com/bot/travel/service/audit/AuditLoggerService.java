// com.bot.travel.service.audit.AuditLogService.java
package com.bot.travel.service.audit;

import com.bot.travel.model.audit.AuditLog;
import com.bot.travel.repository.audit.AuditLoggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AuditLoggerService {

    @Autowired
    private final AuditLoggerRepository auditLoggerRepository;

    public AuditLoggerService(AuditLoggerRepository auditLoggerRepository) {
        this.auditLoggerRepository = auditLoggerRepository;
    }

    /**
     * Fetch all logs with pagination support
     */
    public List<AuditLog> getAllLogs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AuditLog> logs = auditLoggerRepository.findAll(pageable);
        return logs.getContent();
    }

    /**
     * Fetch logs by Model Name with pagination support
     */
    public List<AuditLog> getLogsByModelName(String modelName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AuditLog> logs = auditLoggerRepository.findByModelName(modelName, pageable);
        return logs.getContent();
    }

    /**
     * Fetch logs by User ID with pagination support
     */
    public List<AuditLog> getLogsByUserId(String userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AuditLog> logs = auditLoggerRepository.findByUserId(userId, pageable);
        return logs.getContent();
    }

    /**
     * Fetch logs by Operation Type with pagination support
     */
    public List<AuditLog> getLogsByOperation(String operation, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AuditLog> logs = auditLoggerRepository.findByOperation(operation, pageable);
        return logs.getContent();
    }

    /**
     * Fetch a single log by ID
     */
    public Optional<AuditLog> getLogById(String id) {
        return auditLoggerRepository.findById(id);
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
        auditLoggerRepository.save(log);
}

}
