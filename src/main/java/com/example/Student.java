package com.example;

import javax.swing.JOptionPane;

/**
 *
 * @author Edificio C
 */
public class Student {
    private static int nextId = 1; 
    private final int id;
    private String name;
    private String lastName;
    private float grade;

    public Student(String name, String lastName, float grade) {
        this.id = nextId++;
        this.name = name;
        this.lastName = lastName;
        this.grade = grade;
    }
    public static Student createStudent() {
        String name = Util.getStringInput("Enter student name:");
        String lastName = Util.getStringInput("Enter student last name:");
        float grade = Util.getFloatInput("Enter student grade:");

        if (name == null || lastName == null) {
            return null;
        }

        if (grade < 0 || grade > 100) {
            JOptionPane.showMessageDialog(null, "Invalid grade. Please try again.");
            return createStudent();
        }
        return new Student(name, lastName, grade);
    }
    
    public String toString() {
        return this.id + " - " + this.getFullName() + " " + this.grade;
    }
    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public float getGrade() {
        return this.grade;
    }
    public void setGrade(float grade) {
        this.grade = grade;
    }
    public String getFullName() {
        return this.name + " " + this.lastName;
    }
}
