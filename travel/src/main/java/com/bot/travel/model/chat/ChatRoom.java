package com.bot.travel.model.chat;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "chatrooms")
public class ChatRoom {
    @Id
    private String id;
    private String name;
    private Boolean isGroup;
    private String createdBy;
    private List<String> members;
    private Date createdAt;
    private Date lastActivity;
}