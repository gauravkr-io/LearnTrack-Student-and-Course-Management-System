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

> The application loads sample students, courses, and enrollments automatically at runtime on startup.

## Project Structure

```
src/main/java/com/airtribe/learntrack/
  Main.java
  data/
    SampleDataLoader.java
  constants/
    AppConstants.java
    MenuOptions.java
  entity/
    Person.java
    Student.java
    Trainer.java
    Course.java
    Enrollment.java
  enums/
    CourseStatus.java
    EnrollmentStatus.java
  exception/
    EntityNotFoundException.java
    InvalidInputException.java
  repository/
    StudentRepository.java
    CourseRepository.java
    EnrollmentRepository.java
  service/
    StudentService.java
    CourseService.java
    EnrollmentService.java
  ui/
    ConsoleInput.java
    MenuRenderer.java
  util/
    IdGenerator.java
    InputValidator.java
```

## Class Diagram

```
Person
  ├─ Student
  └─ Trainer

Course

Enrollment

Main
  ├─ data.SampleDataLoader
  ├─ ui.ConsoleInput
  ├─ ui.MenuRenderer
  ├─ service.StudentService -> repository.StudentRepository
  ├─ service.CourseService -> repository.CourseRepository
  └─ service.EnrollmentService
       ├─ repository.EnrollmentRepository
       ├─ service.StudentService
       └─ service.CourseService
```

## Features

- Add, view, search, and deactivate students
- Add, view, activate, and deactivate courses
- Enroll active students into active courses
- View enrollments by student
- Mark enrollments as completed or cancelled
- Clean, modular project structure without external frameworks
