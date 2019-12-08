package com.rozsa.model;

public class EventData {
    private EventType type;

    private String key;

    private Boolean value;

    public EventType getType() {
        if (type == null) {
            type = EventType.SWITCH;
        }
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
