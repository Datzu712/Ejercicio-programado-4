package com.example;

import javax.swing.JOptionPane;

public class Util {
    public static Float getFloatInput(String message) {
        try {
            return Float.parseFloat(JOptionPane.showInputDialog(message));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please try again.");
            return getFloatInput(message);
        }
    }

    public static String getStringInput(String message) {
        String input = JOptionPane.showInputDialog(message);
        if (input == null) {
            return null;
        } else if (input.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please try again.");
            return getStringInput(message);
        }
        return input;
    }

    public static int getIntInput(String message) {
        try {
            return Integer.parseInt(JOptionPane.showInputDialog(message));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please try again.");
            return getIntInput(message);
        }
    }
}
