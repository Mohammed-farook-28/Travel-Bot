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
@Document(collection = "messages")
public class Message {
    @Id
    private String id;
    private String roomId;
    private String senderId;
    private String content;
    private String mediaUrl;
    private List<String> readBy;
    private Date createdAt;
    private MessageType messageType;
}