package com.airtribe.learntrack.util;

public final class IdGenerator {

    private static int studentIdCounter = 1;
    private static int courseIdCounter = 1;
    private static int enrollmentIdCounter = 1;

    private IdGenerator() {
    }

    public static int getNextStudentId() {
        return studentIdCounter++;
    }

    public static int getNextCourseId() {
        return courseIdCounter++;
    }

    public static int getNextEnrollmentId() {
        return enrollmentIdCounter++;
    }
}
