package com.airtribe.learntrack.util;

public final class InputValidator {

    private InputValidator() {
    }

    public static boolean isValidEmail(String email) {
        if (isNullOrBlank(email)) {
            return false;
        }
        String trimmed = email.trim();
        return trimmed.contains("@") && trimmed.contains(".");
    }

    public static boolean isNullOrBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static int parsePositiveInt(String input) {
        if (isNullOrBlank(input)) {
            return -1;
        }
        try {
            int value = Integer.parseInt(input.trim());
            return value > 0 ? value : -1;
        } catch (NumberFormatException exception) {
            return -1;
        }
    }
}
