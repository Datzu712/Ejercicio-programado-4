package com.example;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        Application app = new Application();
        boolean exit = false;
        do {
            String usrInput = JOptionPane.showInputDialog(
                "1. Add student\n" +
                "2. Add course\n" +
                "3. Show mean score\n" +
                "4. Show student with the highest score\n" +
                "5. Show student with the lowest score\n" +
                "6. Paginate students\n" + 
                "7. Show students behind of mean\n" +
                "8. Show students ahead of mean\n" +
                "9. Exit"
            );
            if (usrInput == null) {
                break;
            }

            switch (usrInput) {
                case "1" -> app.addStudent();
                case "2" -> app.addCourse();
                case "3" -> app.showCourseMeanScoreDialog();
                case "4" -> app.showBiggerOrLowerScoreDialog(true);
                case "5" -> app.showBiggerOrLowerScoreDialog(false);
                case "6" -> app.paginateStudents();
                case "7" -> app.showStudentsBehindMeanDialog();
                case "8" -> app.showStudentsAheadMeanDialog();
                case "9" -> exit = true;
                default -> JOptionPane.showMessageDialog(null, "Invalid option", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (!exit);
    }
}