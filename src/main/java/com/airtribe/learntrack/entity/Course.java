package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.enums.CourseStatus;

public class Course {

    private int id;
    private String courseName;
    private String description;
    private int durationInWeeks;
    private CourseStatus status;

    public Course(int id, String courseName, String description, int durationInWeeks, CourseStatus status) {
        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.durationInWeeks = durationInWeeks;
        this.status = status;
    }

    public Course(int id, String courseName, int durationInWeeks, CourseStatus status) {
        this(id, courseName, "No description provided.", durationInWeeks, status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationInWeeks() {
        return durationInWeeks;
    }

    public void setDurationInWeeks(int durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }

    public boolean isActive() {
        return status == CourseStatus.ACTIVE;
    }
}
