package com.techelevator.tenmo.model;

//Maps error message to POJO and prints error message client side
//plain old Java object (POJO) is an ordinary Java object, not bound by any special restriction.
public class ErrorMessage {
    private String message;
    private String timestamp;
    private String status;
    private String error;
    private String path;

    public ErrorMessage(){};

    ErrorMessage(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}