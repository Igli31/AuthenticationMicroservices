package com.example.student_service.Enum;

public enum RoleEnum {

    USER("USER"),
    ADMIN("ADMIN");

    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
