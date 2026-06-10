package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnrollmentRepository {

    private final List<Enrollment> enrollments = new ArrayList<>();

    public void save(Enrollment enrollment) {
        findById(enrollment.getId()).ifPresent(enrollments::remove);
        enrollments.add(enrollment);
    }

    public Optional<Enrollment> findById(int id) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getId() == id)
                .findFirst();
    }

    public List<Enrollment> findAll() {
        return new ArrayList<>(enrollments);
    }

    public List<Enrollment> findByStudentId(int studentId) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getStudentId() == studentId)
                .collect(Collectors.toList());
    }

    public List<Enrollment> findByCourseId(int courseId) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getCourseId() == courseId)
                .collect(Collectors.toList());
    }

    public void deleteById(int id) {
        enrollments.removeIf(enrollment -> enrollment.getId() == id);
    }

    public boolean existsById(int id) {
        return findById(id).isPresent();
    }
}
