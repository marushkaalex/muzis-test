package ru.muzis.muzistest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponseModel<T extends BaseModel> extends BaseModel {
    @JsonProperty("message")
    private MessageModel<T> message;

    public MessageModel<T> getMessage() {
        return message;
    }

    public void setMessage(MessageModel<T> message) {
        this.message = message;
    }
}
