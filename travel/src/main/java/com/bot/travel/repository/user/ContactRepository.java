package com.bot.travel.repository.user;

import com.bot.travel.model.user.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {
    List<Contact> findByUserId(String userId);
    List<Contact> findByNationality(String nationality);
}