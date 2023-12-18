package com.chat.webSocketChat.vo;

public class Message {
    private String type;
    private String sender;
    private String receiver;
    private Object data;

    // default 값 생성
    public Message () {
        this.type = "default";
        this.sender = "default";
        this.receiver = "default";
        this.data = "default";
    }

    public void newConnect() {
        this.type = "new";
    }

    public void closeConnect() {
        this.type = "close";
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "type='" + type + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", data=" + data +
                '}';
    }
}
