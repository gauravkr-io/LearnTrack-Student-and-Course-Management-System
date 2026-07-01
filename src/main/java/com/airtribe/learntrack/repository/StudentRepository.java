package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository {

    private final List<Student> students = new ArrayList<>();

    public void save(Student student) {
        findById(student.getId()).ifPresent(students::remove);
        students.add(student);
    }

    public Optional<Student> findById(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst();
    }

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    public void deleteById(int id) {
        students.removeIf(student -> student.getId() == id);
    }

    public boolean existsById(int id) {
        return findById(id).isPresent();
    }
}
