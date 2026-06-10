package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository {

    private final List<Student> students = new ArrayList<>();

    public Optional<Student> findById(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst();
    }

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    public Student save(Student student) {
        Optional<Student> existing = findById(student.getId());
        if (existing.isPresent()) {
            students.remove(existing.get());
        }
        students.add(student);
        return student;
    }

    public boolean deleteById(int id) {
        return students.removeIf(student -> student.getId() == id);
    }
}
