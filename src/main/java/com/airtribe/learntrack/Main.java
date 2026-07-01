package com.airtribe.learntrack;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.data.SampleDataLoader;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.ui.ConsoleInput;
import com.airtribe.learntrack.ui.MenuRenderer;

import java.util.List;

public class Main {
    private static final StudentService STUDENT_SERVICE;
    private static final CourseService COURSE_SERVICE;
    private static final EnrollmentService ENROLLMENT_SERVICE;

    static {
        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

        STUDENT_SERVICE = new StudentService(studentRepository);
        COURSE_SERVICE = new CourseService(courseRepository);
        ENROLLMENT_SERVICE = new EnrollmentService(enrollmentRepository, STUDENT_SERVICE, COURSE_SERVICE);
        SampleDataLoader.loadSampleData(STUDENT_SERVICE, COURSE_SERVICE, ENROLLMENT_SERVICE);
    }

    public static void main(String[] args) {
        try {
            run();
        } finally {
            ConsoleInput.close();
        }
    }

    private static void run() {
        printWelcome();
        int choice;
        do {
            MenuRenderer.printMainMenu();
            choice = ConsoleInput.readMenuChoice(AppConstants.MENU_PROMPT);
            switch (choice) {
                case MenuOptions.MAIN_STUDENTS -> showStudentMenu();
                case MenuOptions.MAIN_COURSES -> showCourseMenu();
                case MenuOptions.MAIN_ENROLLMENTS -> showEnrollmentMenu();
                case MenuOptions.EXIT -> System.out.println("  [OK] Exiting the application.");
                default -> System.out.println("  [Error] Invalid choice. Please try again.");
            }
        } while (choice != MenuOptions.EXIT);
    }

    private static void printWelcome() {
        System.out.println();
        System.out.println(AppConstants.SEPARATOR);
        System.out.println("  " + AppConstants.APP_NAME + " — Student Management");
        System.out.println(AppConstants.SEPARATOR);
        System.out.println("  [OK] Sample data loaded: students and courses are available.");
    }

    private static void showStudentMenu() {
        int choice;
        do {
            MenuRenderer.printStudentMenu();
            choice = ConsoleInput.readMenuChoice(AppConstants.MENU_PROMPT);
            switch (choice) {
                case MenuOptions.STUDENT_ADD -> handleAddStudent();
                case MenuOptions.STUDENT_VIEW_ALL -> handleViewAllStudents();
                case MenuOptions.STUDENT_SEARCH -> handleSearchStudentById();
                case MenuOptions.STUDENT_DEACTIVATE -> handleDeactivateStudent();
                case MenuOptions.BACK -> {
                }
                default -> System.out.println("  [Error] Invalid student menu choice.");
            }
        } while (choice != MenuOptions.BACK);
    }

    private static void showCourseMenu() {
        int choice;
        do {
            MenuRenderer.printCourseMenu();
            choice = ConsoleInput.readMenuChoice(AppConstants.MENU_PROMPT);
            switch (choice) {
                case MenuOptions.COURSE_ADD -> handleAddCourse();
                case MenuOptions.COURSE_VIEW_ALL -> handleViewAllCourses();
                case MenuOptions.COURSE_ACTIVATE -> handleActivateCourse();
                case MenuOptions.COURSE_DEACTIVATE -> handleDeactivateCourse();
                case MenuOptions.BACK -> {
                }
                default -> System.out.println("  [Error] Invalid course menu choice.");
            }
        } while (choice != MenuOptions.BACK);
    }

    private static void showEnrollmentMenu() {
        int choice;
        do {
            MenuRenderer.printEnrollmentMenu();
            choice = ConsoleInput.readMenuChoice(AppConstants.MENU_PROMPT);
            switch (choice) {
                case MenuOptions.ENROLLMENT_ENROLL -> handleEnrollStudent();
                case MenuOptions.ENROLLMENT_VIEW_BY_STUDENT -> handleViewEnrollmentsForStudent();
                case MenuOptions.ENROLLMENT_MARK_COMPLETED -> handleMarkEnrollmentCompleted();
                case MenuOptions.ENROLLMENT_CANCEL -> handleCancelEnrollment();
                case MenuOptions.BACK -> {
                }
                default -> System.out.println("  [Error] Invalid enrollment menu choice.");
            }
        } while (choice != MenuOptions.BACK);
    }

    private static void handleAddStudent() {
        try {
            String firstName = readLine("  First name: ");
            String lastName = readLine("  Last name: ");
            String email = readLine("  Email: ");
            String batch = readLine("  Batch: ");

            Student student = STUDENT_SERVICE.addStudent(firstName, lastName, email, batch);
            System.out.println("  [OK] Student added successfully.");
            System.out.printf("  %-6s %-25s %-30s %-10s %-10s%n", "ID", "Name", "Email", "Batch", "Status");
            System.out.printf("  %-6d %-25s %-30s %-10s %-10s%n",
                    student.getId(), student.getDisplayName(), student.getEmail(), student.getBatch(), student.isActive() ? "Active" : "Inactive");
        } catch (EntityNotFoundException | InvalidInputException exception) {
            printError(exception);
        }
    }

    private static void handleViewAllStudents() {
        try {
            List<Student> students = STUDENT_SERVICE.getAllStudents();
            if (students.isEmpty()) {
                System.out.println("  [OK] No students are registered yet.");
                return;
            }
            System.out.printf("  %-6s %-25s %-30s %-10s %-10s%n", "ID", "Name", "Email", "Batch", "Status");
            for (Student student : students) {
                System.out.printf("  %-6d %-25s %-30s %-10s %-10s%n",
                        student.getId(), student.getDisplayName(), student.getEmail(), student.getBatch(), student.isActive() ? "Active" : "Inactive");
            }
            System.out.println("  [OK] Student list displayed.");
        } catch (EntityNotFoundException | InvalidInputException exception) {
            printError(exception);
        }
    }

    private static void handleSearchStudentById() {
        try {
            int studentId = readInt("  Enter student ID: ");
            Student student = STUDENT_SERVICE.findStudentById(studentId);
            System.out.println("  [OK] Student found.");
            System.out.printf("  %-6s %-25s %-30s %-10s %-10s%n", "ID", "Name", "Email", "Batch", "Status");
            System.out.printf("  %-6d %-25s %-30s %-10s %-10s%n",
                    student.getId(), student.getDisplayName(), student.getEmail(), student.getBatch(), student.isActive() ? "Active" : "Inactive");
        } catch (EntityNotFoundException | InvalidInputException exception) {
            printError(exception);
        }
    }

    private static void handleDeactivateStudent() {
        try {
            int studentId = readInt("  Enter student ID to deactivate: ");
            STUDENT_SERVICE.deactivateStudent(studentId);
            System.out.println("  [OK] Student has been deactivated.");
        } catch (EntityNotFoundException | InvalidInputException exception) {
            printError(exception);
        }
    }

    private static void handleAddCourse() {
        try {
            String courseName = readLine("  Course name: ");
            String description = readLine("  Description: ");
            int durationInWeeks = readInt("  Duration (weeks): ");

            Course course = COURSE_SERVICE.addCourse(courseName, description, durationInWeeks);
            System.out.println("  [OK] Course added successfully.");
            System.out.printf("  %-6s %-25s %-10s %-10s %-40s%n", "ID", "Course", "Weeks", "Status", "Description");
            System.out.printf("  %-6d %-25s %-10d %-10s %-40s%n",
                    course.getId(), course.getCourseName(), course.getDurationInWeeks(), course.getStatus().getDisplayName(), course.getDescription());
        } catch (EntityNotFoundException | InvalidInputException exception) {
            printError(exception);
        }
    }

    private static void handleViewAllCourses() {
        try {
            List<Course> courses = COURSE_SERVICE.getAllCourses();
            if (courses.isEmpty()) {
                System.out.println("  [OK] No courses are available.");
                return;
            }
            System.out.printf("  %-6s %-25s %-10s %-10s %-40s%n", "ID", "Course", "Weeks", "Status", "Description");
            for (Course course : courses) {
                System.out.printf("  %-6d %-25s %-10d %-10s %-40s%n",
                        course.getId(), course.getCourseName(), course.getDurationInWeeks(), course.getStatus().getDisplayName(), course.getDescription());
            }
            System.out.println("  [OK] Course list displayed.");
        } catch (EntityNotFoundException | InvalidInputException exception) {
            printError(exception);
        }
    }

    private static void handleActivateCourse() {
        try {
            int courseId = readInt("  Enter course ID to activate: ");
            COURSE_SERVICE.activateCourse(courseId);
            System.out.println("  [OK] Course has been activated.");
        } catch (EntityNotFoundException | InvalidInputException exception) {
            printError(exception);
        }
    }

    private static void handleDeactivateCourse() {
        try {
            int courseId = readInt("  Enter course ID to deactivate: ");
            COURSE_SERVICE.deactivateCourse(courseId);
            System.out.println("  [OK] Course has been deactivated.");
        } catch (EntityNotFoundException | InvalidInputException exception) {
            printError(exception);
        }
    }

    private static void handleEnrollStudent() {
        try {
            int studentId = readInt("  Enter student ID: ");
            int courseId = readInt("  Enter course ID: ");

            Enrollment enrollment = ENROLLMENT_SERVICE.enrollStudent(studentId, courseId);
            System.out.println("  [OK] Student enrolled successfully.");
            System.out.printf("  %-12s %-12s %-12s %-12s %-12s%n", "Enroll ID", "Student ID", "Course ID", "Date", "Status");
            System.out.printf("  %-12d %-12d %-12d %-12s %-12s%n",
                    enrollment.getId(), enrollment.getStudentId(), enrollment.getCourseId(), enrollment.getEnrollmentDate(), enrollment.getStatus().getDisplayName());
        } catch (EntityNotFoundException | InvalidInputException exception) {
            printError(exception);
        }
    }

    private static void handleViewEnrollmentsForStudent() {
        try {
            int studentId = readInt("  Enter student ID: ");
            List<Enrollment> enrollments = ENROLLMENT_SERVICE.getEnrollmentsForStudent(studentId);
            if (enrollments.isEmpty()) {
                System.out.println("  [OK] No enrollments found for student ID " + studentId + ".");
                return;
            }
            System.out.printf("  %-12s %-12s %-12s %-12s %-12s%n", "Enroll ID", "Student ID", "Course ID", "Date", "Status");
            for (Enrollment enrollment : enrollments) {
                System.out.printf("  %-12d %-12d %-12d %-12s %-12s%n",
                        enrollment.getId(), enrollment.getStudentId(), enrollment.getCourseId(), enrollment.getEnrollmentDate(), enrollment.getStatus().getDisplayName());
            }
            System.out.println("  [OK] Enrollment list displayed.");
        } catch (EntityNotFoundException | InvalidInputException exception) {
            printError(exception);
        }
    }

    private static void handleMarkEnrollmentCompleted() {
        try {
            int enrollmentId = readInt("  Enter enrollment ID to complete: ");
            ENROLLMENT_SERVICE.markCompleted(enrollmentId);
            System.out.println("  [OK] Enrollment marked as completed.");
        } catch (EntityNotFoundException | InvalidInputException exception) {
            printError(exception);
        }
    }

    private static void handleCancelEnrollment() {
        try {
            int enrollmentId = readInt("  Enter enrollment ID to cancel: ");
            ENROLLMENT_SERVICE.cancelEnrollment(enrollmentId);
            System.out.println("  [OK] Enrollment cancelled.");
        } catch (EntityNotFoundException | InvalidInputException exception) {
            printError(exception);
        }
    }

    private static String readLine(String prompt) {
        return ConsoleInput.readLine(prompt);
    }

    private static int readInt(String prompt) {
        return ConsoleInput.readInt(prompt);
    }

    private static int readMenuChoice(String prompt) {
        return ConsoleInput.readMenuChoice(prompt);
    }

    private static void printError(Exception exception) {
        System.out.println("  [Error] " + exception.getMessage());
    }
}
