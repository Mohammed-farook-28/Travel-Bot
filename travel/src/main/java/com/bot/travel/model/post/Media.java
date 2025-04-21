package com.bot.travel.model.post;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Media {
    private MediaType mediaType;
    private String mediaUrl;
}