// com.bot.travel.model.user.SocialLink.java
package com.bot.travel.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialLink {
    @NotNull
    private SocialPlatform platformType;

    @NotBlank
    @Size(max = 100)
    private String handleOrNumber;
}
