// com.bot.travel.repository.audit.AuditLogRepository.java
package com.bot.travel.repository.audit;

import com.bot.travel.model.audit.AuditLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends MongoRepository<AuditLog, String> {
    List<AuditLog> findByModelName(String modelName);
    List<AuditLog> findByUserId(String userId);
}
