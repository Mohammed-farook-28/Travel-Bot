// com.bot.travel.service.user.ContactService.java
package com.bot.travel.service.user;

import com.bot.travel.model.user.Contact;
import com.bot.travel.repository.user.ContactRepository;
import com.bot.travel.service.audit.AuditLoggerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final AuditLoggerService auditLoggerService;

    public ContactService(ContactRepository contactRepository, AuditLoggerService auditLoggerService) {
        this.contactRepository = contactRepository;
        this.auditLoggerService = auditLoggerService;
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAllByIsDeletedFalse();
    }

    public Optional<Contact> getContactById(String id) {
        return contactRepository.findById(id)
                .filter(contact -> !contact.getIsDeleted());
    }

    @Transactional
    public Contact saveContact(@Valid Contact contact) {
        contact.setCreatedAt(Instant.now());
        contact.setUpdatedAt(Instant.now());
        Contact savedContact = contactRepository.save(contact);

        // 🔍 **Audit Log**
        Map<String, Object> changes = new HashMap<>();
        changes.put("name", contact.getName());
        changes.put("nationality", contact.getNationality());
        auditLoggerService.logEvent("Contact", "CREATE", contact.getUserId(), changes, "SYSTEM");

        return savedContact;
    }

    @Transactional
    public Contact updateContact(String id, @Valid Contact contact) {
        return contactRepository.findById(id)
            .map(existingContact -> {
                contact.setId(id);
                contact.setUpdatedAt(Instant.now());
                Contact updatedContact = contactRepository.save(contact);

                // 🔍 **Audit Log**
                Map<String, Object> changes = new HashMap<>();
                changes.put("name", contact.getName());
                changes.put("nationality", contact.getNationality());
                auditLoggerService.logEvent("Contact", "UPDATE", contact.getUserId(), changes, "SYSTEM");

                return updatedContact;
            })
            .orElseThrow(() -> new RuntimeException("Contact not found with id: " + id));
    }

    @Transactional
    public void softDeleteContact(String id, String deletedBy) {
        contactRepository.findById(id).ifPresent(contact -> {
            contact.setIsDeleted(true);
            contact.setDeletedBy(deletedBy);
            contact.setDeletedAt(Instant.now());
            contactRepository.save(contact);

            // 🔍 **Audit Log**
            Map<String, Object> changes = new HashMap<>();
            changes.put("isDeleted", true);
            auditLoggerService.logEvent("Contact", "DELETE", id, changes, deletedBy);
        });
    }

    @Transactional
    public void restoreContact(String id) {
        contactRepository.findById(id).ifPresent(contact -> {
            contact.setIsDeleted(false);
            contact.setDeletedBy(null);
            contact.setDeletedAt(null);
            contactRepository.save(contact);

            // 🔍 **Audit Log**
            Map<String, Object> changes = new HashMap<>();
            changes.put("isDeleted", false);
            auditLoggerService.logEvent("Contact", "RESTORE", id, changes, "SYSTEM");
        });
    }
}
