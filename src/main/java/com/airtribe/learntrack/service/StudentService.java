package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.List;

public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        if (InputValidator.isNullOrBlank(firstName)) {
            throw new InvalidInputException("First name cannot be blank.");
        }
        if (InputValidator.isNullOrBlank(lastName)) {
            throw new InvalidInputException("Last name cannot be blank.");
        }
        if (!InputValidator.isValidEmail(email)) {
            throw new InvalidInputException("Email address is not valid.");
        }
        if (InputValidator.isNullOrBlank(batch)) {
            throw new InvalidInputException("Batch cannot be blank.");
        }

        Student student = new Student(
                IdGenerator.getNextStudentId(),
                firstName.trim(),
                lastName.trim(),
                email.trim(),
                batch.trim(),
                true
        );
        repository.save(student);
        return student;
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student findStudentById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student", id));
    }

    public Student updateStudentEmail(int id, String newEmail) {
        Student student = findStudentById(id);
        if (!InputValidator.isValidEmail(newEmail)) {
            throw new InvalidInputException("Email address is not valid.");
        }
        student.setEmail(newEmail.trim());
        repository.save(student);
        return student;
    }

    public void deactivateStudent(int id) {
        Student student = findStudentById(id);
        if (!student.isActive()) {
            throw new InvalidInputException("Student is already inactive.");
        }
        student.setActive(false);
        repository.save(student);
    }

    public void deleteStudent(int id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Student", id);
        }
        repository.deleteById(id);
    }
}
