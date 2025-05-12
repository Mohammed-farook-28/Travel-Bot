// com.bot.travel.controller.audit.AuditLogController.java
package com.bot.travel.controller.audit;

import com.bot.travel.model.audit.AuditLog;
import com.bot.travel.repository.audit.AuditLoggerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
public class AuditLogController {

    @Autowired
    private AuditLoggerRepository auditLogRepository;

    /**
     * Get All Logs with Pagination Support
     */
    @GetMapping
    public ResponseEntity<List<AuditLog>> getAllLogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AuditLog> logs = auditLogRepository.findAll(pageable);
        return ResponseEntity.ok(logs.getContent());
    }

    /**
     * Search Logs by Model Name
     */
    @GetMapping("/model/{modelName}")
    public ResponseEntity<List<AuditLog>> getLogsByModelName(
            @PathVariable String modelName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AuditLog> logs = auditLogRepository.findByModelName(modelName, pageable);
        return ResponseEntity.ok(logs.getContent());
    }

    /**
     * Search Logs by User ID
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AuditLog>> getLogsByUserId(
            @PathVariable String userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AuditLog> logs = auditLogRepository.findByUserId(userId, pageable);
        return ResponseEntity.ok(logs.getContent());
    }

    /**
     * Search Logs by Operation Type (CREATE, UPDATE, DELETE, RESTORE)
     */
    @GetMapping("/operation/{operation}")
    public ResponseEntity<List<AuditLog>> getLogsByOperation(
            @PathVariable String operation,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AuditLog> logs = auditLogRepository.findByOperation(operation, pageable);
        return ResponseEntity.ok(logs.getContent());
    }
}
