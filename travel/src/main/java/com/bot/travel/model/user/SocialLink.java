package com.bot.travel.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.bot.travel.model.social.SocialPlatform;

import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialLink {
    private SocialPlatform platformType;
    private String handleOrNumber;
}