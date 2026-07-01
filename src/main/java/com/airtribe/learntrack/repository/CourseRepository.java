package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository {

    private final List<Course> courses = new ArrayList<>();

    public void save(Course course) {
        findById(course.getId()).ifPresent(courses::remove);
        courses.add(course);
    }

    public Optional<Course> findById(int id) {
        return courses.stream()
                .filter(course -> course.getId() == id)
                .findFirst();
    }

    public List<Course> findAll() {
        return new ArrayList<>(courses);
    }

    public void deleteById(int id) {
        courses.removeIf(course -> course.getId() == id);
    }

    public boolean existsById(int id) {
        return findById(id).isPresent();
    }
}
