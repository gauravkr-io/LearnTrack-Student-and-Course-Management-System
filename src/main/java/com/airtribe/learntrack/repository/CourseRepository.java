package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository {

    private final List<Course> courses = new ArrayList<>();

    public Optional<Course> findById(int id) {
        return courses.stream()
                .filter(course -> course.getId() == id)
                .findFirst();
    }

    public List<Course> findAll() {
        return new ArrayList<>(courses);
    }

    public Course save(Course course) {
        Optional<Course> existing = findById(course.getId());
        if (existing.isPresent()) {
            courses.remove(existing.get());
        }
        courses.add(course);
        return course;
    }

    public boolean deleteById(int id) {
        return courses.removeIf(course -> course.getId() == id);
    }
}
