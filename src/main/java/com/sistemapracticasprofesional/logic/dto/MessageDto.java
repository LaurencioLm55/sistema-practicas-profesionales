package com.sistemapracticasprofesional.logic.dto;

import java.time.LocalDate;

public class MessageDto {

    private int messageId;
    private int senderUserId;
    private int receiverUserId;
    private String subject;
    private String content;
    private String senderUserName;
    private String reciverUserName;
    private LocalDate messageDate;

    public MessageDto() {
    }

    public MessageDto(int senderUserId, int receiverUserId, String subject,
                      String content) {

        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.subject = subject;
       
    }

    public MessageDto(int messageId, int senderUserId, int receiverUserId,
                      String subject, String content) {
        this.messageId = messageId;
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.subject = subject;
        this.content = content;
    }


    public MessageDto(int messageId, int senderUserId, int receiverUserId,
                      String subject, String content, String senderUserName, String reciverUserName,
                      LocalDate messageDate) {
        this.messageId = messageId;
        this.senderUserId = senderUserId;
        this.receiverUserId = receiverUserId;
        this.subject = subject;
        this.content = content;
        this.senderUserName = senderUserName;
        this.reciverUserName = reciverUserName;
        this.messageDate = messageDate;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(int senderUserId) {
        this.senderUserId = senderUserId;
    }

    public int getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(int receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

     public String getSenderUserName() {
        return senderUserName;
    }

    public void setSenderUserName(String senderUserName) {
        this.senderUserName = senderUserName;
    }
    
     public String getReciverUserName() {
        return reciverUserName;
    }

    public void setReciverUserName(String reciverUserName) {
        this.reciverUserName = reciverUserName;
    }

    public LocalDate getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(LocalDate messageDate) {
        this.messageDate = messageDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + messageId;
        result = prime * result + senderUserId;
        result = prime * result + receiverUserId;
        result = prime * result + ((subject == null) ? 0 : subject.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((senderUserName == null) ? 0 : senderUserName.hashCode());
        result = prime * result + ((reciverUserName == null) ? 0 : reciverUserName.hashCode());
        result = prime * result + ((messageDate == null) ? 0 : messageDate.hashCode());
        return result;
    } 

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MessageDto other = (MessageDto) obj;
        if (messageId != other.messageId)
            return false;
        if (senderUserId != other.senderUserId)
            return false;
        if (receiverUserId != other.receiverUserId)
            return false;
        if (subject == null) {
            if (other.subject != null)
                return false;
        } else if (!subject.equals(other.subject))
            return false;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (senderUserName == null) {
            if (other.senderUserName != null)
                return false;
        } else if (!senderUserName.equals(other.senderUserName))
            return false;
        if (reciverUserName == null) {
            if (other.reciverUserName != null)
                return false;
        } else if (!reciverUserName.equals(other.reciverUserName))
            return false;
        if (messageDate == null) {
            if (other.messageDate != null)
                return false;
        } else if (!messageDate.equals(other.messageDate))
            return false;
        return true;
    }

}
