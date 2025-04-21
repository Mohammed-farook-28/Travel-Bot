package com.bot.travel.model.post;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private String id;
    private String userId;
    private String content;
    private Date createdAt;
}