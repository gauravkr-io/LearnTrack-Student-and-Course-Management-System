# LearnTrack

LearnTrack is a console-based student and course management system built in core Java. It models a small training administration workflow with separate layers for entities, repositories, services, and the command-line user interface. The application supports student registration, course management, and enrollment tracking with clear business rules and in-memory data handling.

## Prerequisites

- Java 17 or later
- Any Java-aware IDE or terminal environment

## How to Compile

From the repository root:

```bash
javac -cp src/main/java -d out src/main/java/com/airtribe/learntrack/Main.java
```

## How to Run

From the repository root:

```bash
java -cp out com.airtribe.learntrack.Main
```

## Class Diagram

```
Person
  ├─ Student
  └─ Trainer

Course

Enrollment

Main -> StudentService -> StudentRepository
Main -> CourseService -> CourseRepository
Main -> EnrollmentService -> EnrollmentRepository
EnrollmentService -> StudentService
EnrollmentService -> CourseService
```

## Features

- Add, view, search, and deactivate students
- Add, view, activate, and deactivate courses
- Enroll active students into active courses
- View enrollments by student
- Mark enrollments as completed or cancelled
- Clean, modular project structure without external frameworks
