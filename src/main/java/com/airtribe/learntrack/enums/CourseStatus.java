package com.airtribe.learntrack.enums;

public enum CourseStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive");

    private final String displayName;

    CourseStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
