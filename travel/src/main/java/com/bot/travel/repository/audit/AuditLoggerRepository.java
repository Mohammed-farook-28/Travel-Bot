// com.bot.travel.repository.audit.AuditLogRepository.java
package com.bot.travel.repository.audit;

import com.bot.travel.model.audit.AuditLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLoggerRepository extends MongoRepository<AuditLog, String> {
    Page<AuditLog> findByModelName(String modelName, Pageable pageable);
    Page<AuditLog> findByUserId(String userId, Pageable pageable);
    Page<AuditLog> findByOperation(String operation, Pageable pageable);
}
