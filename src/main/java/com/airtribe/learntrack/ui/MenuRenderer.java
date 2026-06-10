package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.constants.AppConstants;

public final class MenuRenderer {

    private MenuRenderer() {
    }

    public static void printHeader(String title) {
        System.out.println();
        System.out.println(AppConstants.SEPARATOR);
        System.out.println(title);
        System.out.println(AppConstants.SEPARATOR);
    }

    public static void printMainMenu() {
        printHeader("  Main Menu");
        System.out.println("  1. Students");
        System.out.println("  2. Courses");
        System.out.println("  3. Enrollments");
        System.out.println("  9. Exit");
        System.out.println(AppConstants.SEPARATOR);
    }

    public static void printStudentMenu() {
        printHeader("  LearnTrack — Student Management");
        System.out.println("  1. Add student");
        System.out.println("  2. View all students");
        System.out.println("  3. Search student by ID");
        System.out.println("  4. Deactivate student");
        System.out.println("  0. Back");
        System.out.println(AppConstants.SEPARATOR);
    }

    public static void printCourseMenu() {
        printHeader("  LearnTrack — Course Management");
        System.out.println("  1. Add course");
        System.out.println("  2. View all courses");
        System.out.println("  3. Activate course");
        System.out.println("  4. Deactivate course");
        System.out.println("  0. Back");
        System.out.println(AppConstants.SEPARATOR);
    }

    public static void printEnrollmentMenu() {
        printHeader("  LearnTrack — Enrollment Management");
        System.out.println("  1. Enroll student in course");
        System.out.println("  2. View enrollments for student");
        System.out.println("  3. Mark enrollment completed");
        System.out.println("  4. Cancel enrollment");
        System.out.println("  0. Back");
        System.out.println(AppConstants.SEPARATOR);
    }
}
