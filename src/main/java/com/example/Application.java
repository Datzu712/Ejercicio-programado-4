package com.example;

import javax.swing.JOptionPane;

public class Application {
    private final CourseService courses;

    public Application() {
        courses = new CourseService();
    }

    public void addCourse() {
        Course newCourse = Course.createCourse();
        if (newCourse == null) {
            return;
        }
        if (this.courses.getCourse(newCourse.getName()) != null) {
            JOptionPane.showMessageDialog(null, "Can't add that course becouse it already exists!");
            return;
        }

        this.courses.add(newCourse);
        JOptionPane.showMessageDialog(null, "Course " + newCourse.getName() + " was added!");
    }
    public void addStudent() {
        Course targetCourse = this.courses.selectCourse();

        if (targetCourse == null) {
            return;
        }

        Student newStudent = Student.createStudent();
        if (newStudent == null) {
            return;
        }
        if (targetCourse.students.getStudent(newStudent.getName()) != null) {
            JOptionPane.showMessageDialog(null, "Student " + newStudent.getName() + " is already in the course " + targetCourse.getName() + "!");
            return;
        }

        targetCourse.students.add(newStudent);

        JOptionPane.showMessageDialog(null, "Student " + newStudent.getName() + " was added to the course " + targetCourse.getName() + "!");;
    }
    public void showCourseMeanScoreDialog() {
        Course targetCourse = this.courses.selectCourse();

        if (targetCourse == null) {
            return;
        }
        double meanScore = targetCourse.students.getMeanScore();

        JOptionPane.showMessageDialog(null,"Mean score: " + meanScore + " of the course " + targetCourse.getName() + "!");
    }
    public void showBiggerOrLowerScoreDialog(boolean bigger) {
        Course targetCourse = this.courses.selectCourse();

        if (targetCourse == null) {

            return;
        }
        Student targetStudent = bigger 
            ? targetCourse.students.getBiggerScore() 
            : targetCourse.students.getLowerStudentGrade();

        if (targetStudent == null) {
            JOptionPane.showMessageDialog(null, "No students were added :(");
            return;
        }
        JOptionPane.showMessageDialog(null,"Student with the highest score is " + targetStudent.getFullName() + " with " + targetStudent.getGrade() + "!");
    }
    public void paginateStudents() {
        Course targetCourse = this.courses.selectCourse();
        if (targetCourse == null) {
            return;
        }
        StudentPaginator.startPaginator(targetCourse.students.getStudents(), this.courses, "Students of the course " + targetCourse.getName());
    }

    public void showStudentsBehindMeanDialog() {
        Course targetCourse = this.courses.selectCourse();
        if (targetCourse == null) {
            return;
        }
        Student[] studentsBehindMean = targetCourse.students.getStudentsBehindMean();
        if (studentsBehindMean.length == 0) {
            JOptionPane.showMessageDialog(null, "No students were added :(");
            return;
        }
        StudentPaginator.startPaginator(studentsBehindMean, this.courses, "Students behind mean (" + targetCourse.students.getMeanScore() + ")");
    }

    public void showStudentsAheadMeanDialog() {
        Course targetCourse = this.courses.selectCourse();
        if (targetCourse == null) {
            return;
        }
        Student[] studentsAboveMean = targetCourse.students.getStudentsAboveMean();
        if (studentsAboveMean.length == 0) {
            JOptionPane.showMessageDialog(null, "No students were added :(");
            return;
        }
        StudentPaginator.startPaginator(studentsAboveMean, this.courses, "Students above mean (" + targetCourse.students.getMeanScore() + ")");
    }
}
