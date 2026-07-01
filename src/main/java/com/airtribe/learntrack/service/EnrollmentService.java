package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.time.LocalDate;
import java.util.List;

public class EnrollmentService {

    private final EnrollmentRepository repository;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrollmentService(EnrollmentRepository repository,
                             StudentService studentService,
                             CourseService courseService) {
        this.repository = repository;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public Enrollment enrollStudent(int studentId, int courseId) {
        Student student = studentService.findStudentById(studentId);
        if (!student.isActive()) {
            throw new InvalidInputException("Student is not active and cannot be enrolled.");
        }
        Course course = courseService.findCourseById(courseId);
        if (!course.isActive()) {
            throw new InvalidInputException("Course is not active.");
        }

        boolean alreadyEnrolled = repository.findByStudentId(studentId).stream()
                .anyMatch(enrollment -> enrollment.getCourseId() == courseId
                        && enrollment.getStatus() == EnrollmentStatus.ACTIVE);
        if (alreadyEnrolled) {
            throw new InvalidInputException("An active enrollment already exists for this student and course.");
        }

        Enrollment enrollment = new Enrollment(
                IdGenerator.getNextEnrollmentId(),
                studentId,
                courseId,
                LocalDate.now(),
                EnrollmentStatus.ACTIVE
        );
        repository.save(enrollment);
        return enrollment;
    }

    public List<Enrollment> getEnrollmentsForStudent(int studentId) {
        studentService.findStudentById(studentId);
        return repository.findByStudentId(studentId);
    }

    public void markCompleted(int enrollmentId) {
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        if (enrollment.getStatus() != EnrollmentStatus.ACTIVE) {
            throw new InvalidInputException("Only active enrollments can be marked as completed.");
        }
        enrollment.setStatus(EnrollmentStatus.COMPLETED);
        repository.save(enrollment);
    }

    public void cancelEnrollment(int enrollmentId) {
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        if (enrollment.getStatus() == EnrollmentStatus.CANCELLED) {
            throw new InvalidInputException("Enrollment is already cancelled.");
        }
        enrollment.setStatus(EnrollmentStatus.CANCELLED);
        repository.save(enrollment);
    }

    private Enrollment findEnrollmentById(int enrollmentId) {
        return repository.findById(enrollmentId)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment", enrollmentId));
    }
}
