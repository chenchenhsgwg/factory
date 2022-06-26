package com.bosssoft.trainee.factory2.common;

public enum FilterType {
    FIELD("field"),
    JOIN("join");
    private final String type;

    FilterType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
