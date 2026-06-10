package com.airtribe.learntrack.entity;

public class Trainer extends Person {

    private String specialization;

    public Trainer() {
        super();
    }

    public Trainer(int id, String firstName, String lastName, String email, String specialization) {
        super(id, firstName, lastName, email);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return String.format("ID: %3d | %s %s | %s | Specialization: %s",
                getId(), getFirstName(), getLastName(), getEmail(), specialization);
    }
}
