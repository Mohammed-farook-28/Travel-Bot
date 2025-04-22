package com.bot.travel.repository.visa;

import com.bot.travel.model.visa.VisaRequirement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisaRequirementRepository extends MongoRepository<VisaRequirement, String> {
    List<VisaRequirement> findByPassportCountry(String passportCountry);
    
    List<VisaRequirement> findByDestinationCountry(String destinationCountry);
    
    Optional<VisaRequirement> findByPassportCountryAndDestinationCountry(
        String passportCountry, String destinationCountry);
    
    List<VisaRequirement> findByPassportCountryAndRequirementType(
        String passportCountry, String requirementType);
    
    List<VisaRequirement> findByRequirementType(String requirementType);
    
    List<VisaRequirement> findByMaxStayDaysGreaterThanEqual(Integer days);
    
    List<VisaRequirement> findByUpdatedAtAfter(Date date);
    
    List<VisaRequirement> findByUpdatedAtBefore(Date date);
}