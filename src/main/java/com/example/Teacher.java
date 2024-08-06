package com.example;

public class Teacher {
    private String name;
    private String lastName;

    public Teacher(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String toString() {
        return this.name + " " + this.lastName;
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
    public String getFullName() {
        return this.name + " " + this.lastName;
    }
}
