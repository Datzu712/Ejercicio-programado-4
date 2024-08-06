package com.example;

import javax.swing.JOptionPane;

public class Course {
    private String name;
    private Teacher teacher;
    public final StudentService students;

    public Course(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
        this.students = new StudentService();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public static Course createCourse() {
        String name = JOptionPane.showInputDialog("Enter the course name");
        String teacherName = JOptionPane.showInputDialog("Enter the teacher name");
        String teacherLastName = JOptionPane.showInputDialog("Enter the teacher last name");
        Teacher teacher = new Teacher(teacherName, teacherLastName);
        Course course = new Course(name, teacher);

        return course;
    }
}
