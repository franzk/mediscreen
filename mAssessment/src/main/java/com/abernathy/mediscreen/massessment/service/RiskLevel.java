package com.abernathy.mediscreen.massessment.service;

public enum RiskLevel {
    LEVEL_0(0, "None"),
    LEVEL_1(1, "Borderline"),
    LEVEL_2(2, "In Danger"),
    LEVEL_3(3, "Early onset");
    private int value;
    private String message;

    private RiskLevel(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}