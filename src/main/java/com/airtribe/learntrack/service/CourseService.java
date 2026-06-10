package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.enums.CourseStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.List;

public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public Course addCourse(String courseName, String description, int durationInWeeks) {
        if (InputValidator.isNullOrBlank(courseName)) {
            throw new InvalidInputException("Course name cannot be blank.");
        }
        if (durationInWeeks <= 0) {
            throw new InvalidInputException("Duration must be greater than zero.");
        }
        String courseDescription = InputValidator.isNullOrBlank(description)
                ? "No description provided."
                : description.trim();

        Course course = new Course(
                IdGenerator.getNextCourseId(),
                courseName.trim(),
                courseDescription,
                durationInWeeks,
                CourseStatus.ACTIVE
        );
        repository.save(course);
        return course;
    }

    public List<Course> getAllCourses() {
        return repository.findAll();
    }

    public Course findCourseById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course", id));
    }

    public void activateCourse(int id) {
        Course course = findCourseById(id);
        if (course.isActive()) {
            throw new InvalidInputException("Course is already active.");
        }
        course.setStatus(CourseStatus.ACTIVE);
        repository.save(course);
    }

    public void deactivateCourse(int id) {
        Course course = findCourseById(id);
        if (!course.isActive()) {
            throw new InvalidInputException("Course is already inactive.");
        }
        course.setStatus(CourseStatus.INACTIVE);
        repository.save(course);
    }
}
