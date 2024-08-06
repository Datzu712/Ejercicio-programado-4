package com.example;

import javax.swing.JOptionPane;

public class StudentPaginator {
    private Student[] students;
    private int currentPage = 0;
    private int pageSize = ApplicationConfig.DEFAULT_PAGE_SIZE;
    private int totalPages;
    public CourseService courses;

    private  StudentPaginator(Student[] students, CourseService courses) {
        this.courses = courses;
        this.students = students;
        this.currentPage = 0;
        this.totalPages = (int) Math.ceil((double) students.length / pageSize);
    }

    public static void startPaginator(Student[] students, CourseService courses, String title) {
        StudentPaginator paginator = new StudentPaginator(students, courses);

        boolean exit = false;
        do {
            String usrInput = JOptionPane.showInputDialog(
                title + "\n" +
                "[n]. Next page\n" +
                "[b]. Previous page\n" +
                "[g]. Go to page\n" +
                "[e]. Exit" + "\n" +
                "[c]. Change course\n" +
                "--------------------\n" +
                paginator.getText()
            );
            if (usrInput == null) {
                usrInput = "e";
            }

            switch (usrInput) {
                case "n":
                    paginator.currentPage = Math.min(paginator.currentPage + 1, paginator.totalPages - 1);
                    break;
                case "b":
                    paginator.currentPage = Math.max(paginator.currentPage - 1, 0);
                    break;
                case "g":
                    int page = Util.getIntInput("Enter page number:");
                    if (page < 0 || page >= paginator.totalPages) {
                        JOptionPane.showMessageDialog(null, "Invalid page number");
                    } else {
                        paginator.currentPage = page -1;
                    }
                    break;
                case "e":
                    exit = true;
                    break;
                case "c":
                    Course targetCourse = paginator.courses.selectCourse();
                    if (targetCourse == null) {
                        break;
                    }
                    paginator.students = targetCourse.students.getStudents();
                    paginator.currentPage = 0;
                    paginator.totalPages = (int) Math.ceil((double) paginator.students.length / paginator.pageSize);
                    title = "Students of the course " + targetCourse.getName();

                    break;
                default: 
                    JOptionPane.showMessageDialog(null, "Invalid option");
            }
        } while (!exit);
    }

    public String getText() {
        int beginIndex = currentPage * pageSize;
        int endIndex = Math.min(beginIndex + pageSize, students.length);

       String text = "";
        for (int i = beginIndex; i < endIndex; i++) {
           text += students[i].toString() + "\n";
        }
        text += "Page " + (currentPage + 1) + " of " + totalPages;

        return text;
    }
}
