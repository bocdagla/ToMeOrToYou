package com.tomeortoyou.entities;

public final class Message {
    private String senderId;
    private String content;

    public Message() {
    }

    public Message(User user, String content) {
        this.senderId = user.getId();
        this.content = content;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                ", senderId='" + senderId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
