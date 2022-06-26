package com.bosssoft.trainee.factory2.common;

import java.util.HashMap;

public class Response extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public Response message(String message) {
        this.put("message", message);
        return this;
    }

    public Response code(String code) {
        this.put("code", code);
        return this;
    }

    public Response status(String status) {
        this.put("status", status);
        return this;
    }

    public Response data(Object data) {
        this.put("data", data);
        return this;
    }

    @Override
    public Response put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Response addCodeMessage(Integer code, String message, String status, Object data) {
        this.put("code", code);
        this.put("message", message);
        this.put("status", status);
        this.put("data", data);
        return this;
    }

    public Response addCodeMessage(Integer code, String message, String status) {
        this.put("code", code);
        this.put("message", message);
        this.put("status", status);
        return this;
    }
}
