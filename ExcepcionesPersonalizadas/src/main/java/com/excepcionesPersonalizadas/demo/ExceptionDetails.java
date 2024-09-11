package com.excepcionesPersonalizadas.demo;

public class ExceptionDetails {

    private String userMessage;
    private String severity;

    public ExceptionDetails(String severity, String userMessage) {
        this.severity = severity;
        this.userMessage = userMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
