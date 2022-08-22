package com.ddkirill.strore.entity;

public enum StatusEnum {
    WAIT("wait"),
    DONE("done");

    private final String status;

    StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
