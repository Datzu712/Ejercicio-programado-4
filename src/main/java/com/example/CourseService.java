package com.example;

import javax.swing.JOptionPane;

public class CourseService {
    private Course[] courses;

    public CourseService() {
        courses = new Course[ApplicationConfig.DEFAULT_ARR_SIZE];

        if (ApplicationConfig.TEST_MODE) {
            this.addTestCourses();
        }
    }

    private void resize(int newSize) {
        if (newSize <= this.courses.length) {
            throw new IllegalArgumentException("El nuevo tamaño es menor al actual");
        }
        Course[] newList = new Course[newSize];
        for (int i = 0; i < this.courses.length; i++) {
            newList[i] = this.courses[i];
        }
        this.courses = newList;
    }

    public void add(Course... newCourses) {
        for (int i = 0; i < newCourses.length; i++) {
            this.add(newCourses[i]);
        }
    }
    public void add(Course newCourse) {
        if (newCourse == null) {
            throw new IllegalArgumentException("El curso no puede ser nulo");
        }
        boolean maxSizeExceeded = true;
        int nextEmptyIndex = 0;
        for (int i = 0; i < this.courses.length; i++) {
            if (this.courses[i] == null) {
                maxSizeExceeded = false;
                nextEmptyIndex = i;
                break;
            }
        }
        if (maxSizeExceeded) {
            nextEmptyIndex = this.courses.length;
            this.resize(ApplicationConfig.DEFAULT_ARR_SIZE + this.courses.length);
        }
        this.courses[nextEmptyIndex] = newCourse;
    }
    private void addTestCourses() {
        Teacher mathTeacher = new Teacher("Yendri", "Nuñuez");
        Teacher chemistryTeacher = new Teacher("Carlos", "Soto");
        Teacher englishTeacher = new Teacher("Leonardo", "Valverde");

        Course mathCourse = new Course("Math", mathTeacher);
        Course chemistryCourse = new Course("Chemistry", chemistryTeacher);
        Course englishCourse = new Course("English", englishTeacher);

        this.add(mathCourse, chemistryCourse, englishCourse);
    }
    public int getCoursesSize() {
        int count = 0;
        for (int i = 0; i < this.courses.length; i++) {
            if (this.courses[i] != null) {
                count++;
            }
        }
        return count;
    }
    public void addCourse() {
        Course newCourse = Course.createCourse();
        if (newCourse == null) {
            return;
        }
        this.add(newCourse);
        JOptionPane.showMessageDialog(null, "Course added successfully");
    }
    public Course selectCourse() {
        if (this.getCoursesSize() == 0) {
            JOptionPane.showMessageDialog(null, "No courses were added :(", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        String courseNames = "";
        for (int i = 0; i < this.getCoursesSize(); i++) {
            Course currentCourse = this.courses[i];

            courseNames += currentCourse.getName() + " (" + currentCourse.students.getSize() + " students) - " + currentCourse.getTeacher().getFullName() + "\n";
        }
        String selectedCourse = JOptionPane.showInputDialog(null, "Select a course:\n" + courseNames);
        if (selectedCourse == null) {
            return null;
        }
        Course targetCourse = null;
        for (int i = 0; i < this.getCoursesSize(); i++) {
            if (this.courses[i].getName().toLowerCase().equals(selectedCourse.toLowerCase())) {
                targetCourse = this.courses[i];
                break;
            }
        }
        if (targetCourse == null) {
            JOptionPane.showMessageDialog(null, "Course not found");
        }
        return targetCourse;
    }
    public Course getCourse(String courseName) {
        for (int i = 0; i < this.getCoursesSize(); i++) {
            if (this.courses[i].getName().toLowerCase().equals(courseName.toLowerCase())) {
                return this.courses[i];
            }
        }
        return null;
    }
}
