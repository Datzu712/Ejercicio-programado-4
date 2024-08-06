package com.example;

import java.util.Random;

public class StudentService {
    private  Student[] students;

    public StudentService() {
        students = new Student[ApplicationConfig.DEFAULT_ARR_SIZE];

        if (ApplicationConfig.TEST_MODE) {
            this.addTestStudents();
        }
    }
    public void add(Student newStudent) {
        if (newStudent == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        boolean maxSizeExceeded = true;
        int nextEmptyIndex = 0;
        for (int i = 0; i < this.students.length; i++) {
            if (this.students[i] == null) {
                maxSizeExceeded = false;
                nextEmptyIndex = i;
                break;
            }
        }
        if (maxSizeExceeded) {
            nextEmptyIndex = this.students.length;
            this.resize(ApplicationConfig.DEFAULT_ARR_SIZE + this.students.length);
        }
        this.students[nextEmptyIndex] = newStudent;
    }
    private void resize(int newSize) {
        if (newSize <= this.students.length) {
            throw new IllegalArgumentException("El nuevo tamaño es menor al actual");
        }
        Student[] newList = new Student[newSize];
        for (int i = 0; i < this.students.length; i++) {
            newList[i] = this.students[i];
        }
        this.students = newList;
    }
    public int getMeanScore() {
        int sum = 0;
        int count = 0;
        for (int i = 0; i < this.students.length; i++) {
            if (this.students[i] != null) {
                sum += this.students[i].getGrade();
                count++;
            }
        }
        if (count == 0) {
            return 0;
        }
        return sum / count;
    }
    public Student getBiggerScore() {
        Student bigger = null;
        for (int i = 0; i < this.students.length; i++) {
            if (this.students[i] != null) {
                if (bigger == null || this.students[i].getGrade() > bigger.getGrade()) {
                    bigger = this.students[i];
                }
            }
        }
        return bigger;
    }
    public Student getLowerStudentGrade() {
        Student lower = null;
        for (int i = 0; i < this.students.length; i++) {
            if (this.students[i] != null) {
                if (lower == null || this.students[i].getGrade() < lower.getGrade()) {
                    lower = this.students[i];
                }
            }
        }
        return lower;
    }
    public Student[] getFailedStudents() {
        int failedStudentsLen = 0;
        for (int i = 0; i < this.students.length; i++) {
            if (this.students[i] != null && this.students[i].getGrade() < ApplicationConfig.PASS_GRADE) {
                failedStudentsLen++;
            }
        }
        Student[] failedStudents = new Student[failedStudentsLen];
        int nextIndex = 0;
        for (int i = 0; i < this.students.length; i++) {
            if (this.students[i] != null && this.students[i].getGrade() < ApplicationConfig.PASS_GRADE) {
                failedStudents[nextIndex] = this.students[i];
                nextIndex++;
            }
        }
        return failedStudents;
    }
    public Student[] getPassedStudents() {
        int passedStudentsLen = 0;
        for (int i = 0; i < this.students.length; i++) {
            if (this.students[i] != null && this.students[i].getGrade() >= ApplicationConfig.PASS_GRADE) {
                passedStudentsLen++;
            }
        }
        Student[] passedStudents = new Student[passedStudentsLen];
        int nextIndex = 0;
        for (int i = 0; i < this.students.length; i++) {
            if (this.students[i] != null && this.students[i].getGrade() >= ApplicationConfig.PASS_GRADE) {
                passedStudents[nextIndex] = this.students[i];
                nextIndex++;
            }
        }
        return passedStudents;
    }
    public Student[] getStudents() {
        int notNullStudents = 0;
        for (int i = 0; i < this.students.length; i++) {
            if (this.students[i] != null) {
                notNullStudents++;
            }
        }
        Student[] students = new Student[notNullStudents];
        int nextIndex = 0;
        for (int i = 0; i < this.students.length; i++) {
            if (this.students[i] != null) {
                students[nextIndex] = this.students[i];
                nextIndex++;
            }
        }
        return students;
    }
    private void addTestStudents() {
        if (!ApplicationConfig.TEST_MODE) {
            throw new IllegalStateException("Cannot add test students in production mode");
        }
        Random random = new Random();

        this.add(new Student("John", "Alfredo",  random.nextInt(100)));
        this.add(new Student("Jane", "Manuel", random.nextInt(100)));
        this.add(new Student("Alice", "Dalas", random.nextInt(100)));
        this.add(new Student("Bob", "Ronaldo", random.nextInt(100)));
        this.add(new Student("Charlie", "Maduro", random.nextInt(100)));
        this.add(new Student("David", "Duglas", random.nextInt(100)));
        this.add(new Student("Eve", "Cobal", random.nextInt(100)));
        this.add(new Student("Frank", "xd", random.nextInt(100)));
        this.add(new Student("Grace", "dace", random.nextInt(100)));
        this.add(new Student("Hank", "tamal", random.nextInt(100)));
        this.add(new Student("Ivy", "si", random.nextInt(100)));
        this.add(new Student("Jack", "Doe",  random.nextInt(100)));
        this.add(new Student("Kurt", "Cobain", random.nextInt(100)));
        this.add(new Student("Lana", "Del Rey", random.nextInt(100)));
        this.add(new Student("Chester", "bennington", random.nextInt(100)));
    }
    public int getSize() {
        int count = 0;
        for (int i = 0; i < this.students.length; i++) {
            if (this.students[i] != null) {
                count++;
            }
        }
        return count;
    }
    public Student[] getStudentsBehindMean() {
        int mean = this.getMeanScore();
        int studentsBehindMeanLen = 0;
        for (int i = 0; i < this.students.length; i++) {
            if (this.students[i] != null && this.students[i].getGrade() < mean) {
                studentsBehindMeanLen++;
            }
        }
        Student[] studentsBehindMean = new Student[studentsBehindMeanLen];
        int nextIndex = 0;
        for (int i = 0; i < this.students.length; i++) {
            if (this.students[i] != null && this.students[i].getGrade() < mean) {
                studentsBehindMean[nextIndex] = this.students[i];
                nextIndex++;
            }
        }
        return studentsBehindMean;
    }
    public Student[] getStudentsAboveMean() {
        int mean = this.getMeanScore();
        int studentsAheadMeanLen = 0;
        for (int i = 0; i < this.students.length; i++) {
            if (this.students[i] != null && this.students[i].getGrade() >= mean) {
                studentsAheadMeanLen++;
            }
        }
        Student[] studentsAheadMean = new Student[studentsAheadMeanLen];
        int nextIndex = 0;
        for (int i = 0; i < this.students.length; i++) {
            if (this.students[i] != null && this.students[i].getGrade() >= mean) {
                studentsAheadMean[nextIndex] = this.students[i];
                nextIndex++;
            }
        }
        return studentsAheadMean;
    }
    public Student getStudent(String targetStudentName) {
        if (this.getSize() == 0) {
            return null;
        }

        for (int i = 0; i < this.students.length; i++) {
            if (this.students[i] != null && this.students[i].getName().equalsIgnoreCase(targetStudentName)) {
                return this.students[i];
            }
        }

        return null;
    }
}
