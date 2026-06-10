package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.time.LocalDate;
import java.util.List;

public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentService studentService,
                             CourseService courseService) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enrollStudent(int studentId, int courseId) {
        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourseById(courseId);

        if (!student.isActive()) {
            throw new InvalidInputException("Cannot enroll an inactive student.");
        }
        if (!course.isActive()) {
            throw new InvalidInputException("Cannot enroll in an inactive course.");
        }
        if (hasActiveEnrollment(studentId, courseId)) {
            throw new InvalidInputException("Student is already actively enrolled in this course.");
        }

        Enrollment enrollment = new Enrollment(
                IdGenerator.getNextEnrollmentId(),
                studentId,
                courseId,
                LocalDate.now()
        );
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        studentService.getStudentById(studentId);
        return enrollmentRepository.findByStudentId(studentId);
    }

    public Enrollment updateEnrollmentStatus(int enrollmentId, EnrollmentStatus newStatus) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Enrollment with ID " + enrollmentId + " not found."));

        if (newStatus == EnrollmentStatus.ACTIVE) {
            throw new InvalidInputException("Cannot reactivate an enrollment from the menu.");
        }
        if (enrollment.getStatus() != EnrollmentStatus.ACTIVE) {
            throw new InvalidInputException("Only active enrollments can be updated.");
        }

        enrollment.setStatus(newStatus);
        return enrollmentRepository.save(enrollment);
    }

    private boolean hasActiveEnrollment(int studentId, int courseId) {
        return enrollmentRepository.findByStudentId(studentId).stream()
                .anyMatch(enrollment -> enrollment.getCourseId() == courseId
                        && enrollment.getStatus() == EnrollmentStatus.ACTIVE);
    }
}
