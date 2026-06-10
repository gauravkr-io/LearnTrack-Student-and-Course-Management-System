package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.enums.CourseStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.List;

public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(String courseName, String description, int durationInWeeks) {
        InputValidator.requireNonBlank(courseName, "Course name");
        InputValidator.requireNonBlank(description, "Description");
        InputValidator.requirePositive(durationInWeeks, "Duration in weeks");

        Course course = new Course(
                IdGenerator.getNextCourseId(),
                courseName.trim(),
                description.trim(),
                durationInWeeks
        );
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(int id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course with ID " + id + " not found."));
    }

    public Course toggleCourseStatus(int id) {
        Course course = getCourseById(id);
        CourseStatus newStatus = course.getStatus() == CourseStatus.ACTIVE
                ? CourseStatus.INACTIVE
                : CourseStatus.ACTIVE;
        course.setStatus(newStatus);
        return courseRepository.save(course);
    }
}
