package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnrollmentRepository {

    private final List<Enrollment> enrollments = new ArrayList<>();

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

    public Enrollment save(Enrollment enrollment) {
        Optional<Enrollment> existing = findById(enrollment.getId());
        if (existing.isPresent()) {
            enrollments.remove(existing.get());
        }
        enrollments.add(enrollment);
        return enrollment;
    }

    public boolean deleteById(int id) {
        return enrollments.removeIf(enrollment -> enrollment.getId() == id);
    }
}
