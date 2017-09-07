package ru.muzis.muzistest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HeaderModel extends BaseModel {
    @JsonProperty("status_code")
    private int statusCode;
    @JsonProperty("execute_time")
    private double executeTime;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public double getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(double executeTime) {
        this.executeTime = executeTime;
    }
}
