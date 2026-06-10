package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.List;

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        InputValidator.requireNonBlank(firstName, "First name");
        InputValidator.requireNonBlank(lastName, "Last name");
        InputValidator.requireValidEmail(email);
        InputValidator.requireNonBlank(batch, "Batch");

        Student student = new Student(
                IdGenerator.getNextStudentId(),
                firstName.trim(),
                lastName.trim(),
                email.trim(),
                batch.trim()
        );
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student with ID " + id + " not found."));
    }

    public Student deactivateStudent(int id) {
        Student student = getStudentById(id);
        if (!student.isActive()) {
            return student;
        }
        student.setActive(false);
        return studentRepository.save(student);
    }
}
