package com.airtribe.learntrack.data;

import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

public final class SampleDataLoader {

    private SampleDataLoader() {
        // Utility class
    }

    public static void loadSampleData(StudentService studentService,
                                      CourseService courseService,
                                      EnrollmentService enrollmentService) {
        studentService.addStudent("Steve", "Rogers", "steve.rogers@example.com", "Batch Alpha");
        studentService.addStudent("Toby", "Stark", "toby.stark@example.com", "Batch Quantum");
        studentService.addStudent("Tony", "Stark", "tony.stark@example.com", "Batch Quantum");
        studentService.addStudent("Barry", "Allen", "barry.allen@example.com", "Batch Velocity");
        studentService.addStudent("Diana", "Prince", "diana.prince@example.com", "Batch Justice");
        studentService.addStudent("Bruce", "Wayne", "bruce.wayne@example.com", "Batch Night");
        studentService.addStudent("Clark", "Kent", "clark.kent@example.com", "Batch Sentinel");

        courseService.addCourse("Java Fundamentals", "Core Java concepts and syntax.", 6);
        courseService.addCourse("Data Structures", "Introduction to data structures and algorithms.", 8);
        courseService.addCourse("Database Basics", "SQL and relational database fundamentals.", 5);

        enrollmentService.enrollStudent(1001, 1001);
        enrollmentService.enrollStudent(1002, 1002);
        enrollmentService.enrollStudent(1003, 1001);
        enrollmentService.enrollStudent(1004, 1002);
        enrollmentService.enrollStudent(1005, 1003);
        enrollmentService.enrollStudent(1006, 1001);
        enrollmentService.enrollStudent(1007, 1003);
    }
}
