package com.example.pwpolicy.controllers;

public class PolicyError {

    private String messageKey;
    private int value;

    public PolicyError(String messageKey, int value) {
        this.messageKey = messageKey;
        this.value = value;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
