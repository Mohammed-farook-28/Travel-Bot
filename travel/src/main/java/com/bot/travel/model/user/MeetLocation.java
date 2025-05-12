// com.bot.travel.model.user.MeetLocation.java
package com.bot.travel.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetLocation {
    @NotBlank
    private String countryId;

    @NotBlank
    private String placeName;
}
