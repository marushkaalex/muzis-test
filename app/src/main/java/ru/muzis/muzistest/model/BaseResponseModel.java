package ru.muzis.muzistest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponseModel<T extends BaseModel> extends BaseModel {
    @JsonProperty("header")
    private HeaderModel header;
    @JsonProperty("body")
    private T body;

    public HeaderModel getHeader() {
        return header;
    }

    public void setHeader(HeaderModel header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}
