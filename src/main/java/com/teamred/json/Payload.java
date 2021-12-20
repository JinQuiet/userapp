package com.teamred.json;

import java.util.Map;

public class Payload<T> {
    private T payload;
    private Map<String, String> errors;

    public Payload(T payload, Map<String, String> errors) {
        this.payload = payload;
        this.errors = errors;
    }
    
    public T getPayload() {
        return payload;
    }
    public void setPayload(T payload) {
        this.payload = payload;
    }
    public Map<String, String> getErrors() {
        return errors;
    }
    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }   
}