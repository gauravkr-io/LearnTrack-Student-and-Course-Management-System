package com.airtribe.learntrack;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {

    private final Scanner scanner;
    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    public Main() {
        this.scanner = new Scanner(System.in);
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

        this.studentService = new StudentService(studentRepository);
        this.courseService = new CourseService(courseRepository);
        this.enrollmentService = new EnrollmentService(
                enrollmentRepository, studentService, courseService);
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    private void run() {
        printWelcome();
        int choice;
        do {
            printMainMenu();
            choice = readInt("Enter your choice: ");
            switch (choice) {
                case MenuOptions.MAIN_STUDENTS -> showStudentMenu();
                case MenuOptions.MAIN_COURSES -> showCourseMenu();
                case MenuOptions.MAIN_ENROLLMENTS -> showEnrollmentMenu();
                case MenuOptions.EXIT -> System.out.println("Thank you for using LearnTrack. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != MenuOptions.EXIT);
    }

    private void printWelcome() {
        System.out.println();
        System.out.println(AppConstants.SEPARATOR);
        System.out.println("  " + AppConstants.APP_NAME + " - " + AppConstants.APP_TAGLINE);
        System.out.println(AppConstants.SEPARATOR);
        System.out.println();
    }

    private void printMainMenu() {
        System.out.println(AppConstants.SEPARATOR);
        System.out.println("MAIN MENU");
        System.out.println(AppConstants.SEPARATOR);
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Enrollments");
        System.out.println("0. Exit");
        System.out.println(AppConstants.SEPARATOR);
    }

    private void showStudentMenu() {
        int choice;
        do {
            System.out.println();
            System.out.println(AppConstants.SEPARATOR);
            System.out.println("STUDENT MENU");
            System.out.println(AppConstants.SEPARATOR);
            System.out.println("1. Add student");
            System.out.println("2. View all students");
            System.out.println("3. Search student by ID");
            System.out.println("4. Deactivate student");
            System.out.println("0. Back");
            System.out.println(AppConstants.SEPARATOR);

            choice = readInt("Enter your choice: ");
            switch (choice) {
                case MenuOptions.STUDENT_ADD -> addStudent();
                case MenuOptions.STUDENT_VIEW_ALL -> viewAllStudents();
                case MenuOptions.STUDENT_SEARCH_BY_ID -> searchStudentById();
                case MenuOptions.STUDENT_DEACTIVATE -> deactivateStudent();
                case MenuOptions.EXIT -> { }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != MenuOptions.EXIT);
    }

    private void showCourseMenu() {
        int choice;
        do {
            System.out.println();
            System.out.println(AppConstants.SEPARATOR);
            System.out.println("COURSE MENU");
            System.out.println(AppConstants.SEPARATOR);
            System.out.println("1. Add course");
            System.out.println("2. View all courses");
            System.out.println("3. Activate / Deactivate course");
            System.out.println("0. Back");
            System.out.println(AppConstants.SEPARATOR);

            choice = readInt("Enter your choice: ");
            switch (choice) {
                case MenuOptions.COURSE_ADD -> addCourse();
                case MenuOptions.COURSE_VIEW_ALL -> viewAllCourses();
                case MenuOptions.COURSE_TOGGLE_STATUS -> toggleCourseStatus();
                case MenuOptions.EXIT -> { }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != MenuOptions.EXIT);
    }

    private void showEnrollmentMenu() {
        int choice;
        do {
            System.out.println();
            System.out.println(AppConstants.SEPARATOR);
            System.out.println("ENROLLMENT MENU");
            System.out.println(AppConstants.SEPARATOR);
            System.out.println("1. Enroll student in course");
            System.out.println("2. View enrollments for student");
            System.out.println("3. Mark enrollment completed / cancelled");
            System.out.println("0. Back");
            System.out.println(AppConstants.SEPARATOR);

            choice = readInt("Enter your choice: ");
            switch (choice) {
                case MenuOptions.ENROLLMENT_ENROLL -> enrollStudent();
                case MenuOptions.ENROLLMENT_VIEW_BY_STUDENT -> viewEnrollmentsForStudent();
                case MenuOptions.ENROLLMENT_UPDATE_STATUS -> updateEnrollmentStatus();
                case MenuOptions.EXIT -> { }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != MenuOptions.EXIT);
    }

    private void addStudent() {
        try {
            String firstName = readLine("First name: ");
            String lastName = readLine("Last name: ");
            String email = readLine("Email: ");
            String batch = readLine("Batch: ");

            Student student = studentService.addStudent(firstName, lastName, email, batch);
            System.out.println("Student added successfully.");
            System.out.println(student);
        } catch (Exception exception) {
            printError(exception);
        }
    }

    private void viewAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            if (students.isEmpty()) {
                System.out.println("No students found.");
                return;
            }
            System.out.println(AppConstants.SEPARATOR);
            students.forEach(System.out::println);
            System.out.println(AppConstants.SEPARATOR);
        } catch (Exception exception) {
            printError(exception);
        }
    }

    private void searchStudentById() {
        try {
            int studentId = readInt("Enter student ID: ");
            Student student = studentService.getStudentById(studentId);
            System.out.println(student);
        } catch (Exception exception) {
            printError(exception);
        }
    }

    private void deactivateStudent() {
        try {
            int studentId = readInt("Enter student ID to deactivate: ");
            Student student = studentService.deactivateStudent(studentId);
            System.out.println("Student deactivated successfully.");
            System.out.println(student);
        } catch (Exception exception) {
            printError(exception);
        }
    }

    private void addCourse() {
        try {
            String courseName = readLine("Course name: ");
            String description = readLine("Description: ");
            int durationInWeeks = readInt("Duration (weeks): ");

            Course course = courseService.addCourse(courseName, description, durationInWeeks);
            System.out.println("Course added successfully.");
            System.out.println(course);
        } catch (Exception exception) {
            printError(exception);
        }
    }

    private void viewAllCourses() {
        try {
            List<Course> courses = courseService.getAllCourses();
            if (courses.isEmpty()) {
                System.out.println("No courses found.");
                return;
            }
            System.out.println(AppConstants.SEPARATOR);
            courses.forEach(System.out::println);
            System.out.println(AppConstants.SEPARATOR);
        } catch (Exception exception) {
            printError(exception);
        }
    }

    private void toggleCourseStatus() {
        try {
            int courseId = readInt("Enter course ID: ");
            Course course = courseService.toggleCourseStatus(courseId);
            System.out.println("Course status updated successfully.");
            System.out.println(course);
        } catch (Exception exception) {
            printError(exception);
        }
    }

    private void enrollStudent() {
        try {
            int studentId = readInt("Enter student ID: ");
            int courseId = readInt("Enter course ID: ");

            Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
            System.out.println("Enrollment created successfully.");
            System.out.println(enrollment);
        } catch (Exception exception) {
            printError(exception);
        }
    }

    private void viewEnrollmentsForStudent() {
        try {
            int studentId = readInt("Enter student ID: ");
            List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(studentId);
            if (enrollments.isEmpty()) {
                System.out.println("No enrollments found for student ID " + studentId + ".");
                return;
            }
            System.out.println(AppConstants.SEPARATOR);
            enrollments.forEach(System.out::println);
            System.out.println(AppConstants.SEPARATOR);
        } catch (Exception exception) {
            printError(exception);
        }
    }

    private void updateEnrollmentStatus() {
        try {
            int enrollmentId = readInt("Enter enrollment ID: ");
            System.out.println("1. Mark as COMPLETED");
            System.out.println("2. Mark as CANCELLED");
            int statusChoice = readInt("Enter your choice: ");

            EnrollmentStatus newStatus = switch (statusChoice) {
                case 1 -> EnrollmentStatus.COMPLETED;
                case 2 -> EnrollmentStatus.CANCELLED;
                default -> throw new IllegalArgumentException("Invalid status choice.");
            };

            Enrollment enrollment = enrollmentService.updateEnrollmentStatus(enrollmentId, newStatus);
            System.out.println("Enrollment status updated successfully.");
            System.out.println(enrollment);
        } catch (Exception exception) {
            printError(exception);
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private void printError(Exception exception) {
        System.out.println("Error: " + exception.getMessage());
    }
}
