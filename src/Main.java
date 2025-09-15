/**
 * MAD204-01 Lab 1
 * Author: Ramandeep Singh
 * Student ID: A00194321
 * Date: 14/09/2025
 * Description:
 *  Console Gradebook & Utilities Application. Users can add students,
 *  enter grades, compute averages/letter grades, and run utility demos
 *  showing operator precedence, type casting, and recursion.
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main application class for the Gradebook & Utilities App.
 */
public class Main {
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    /**
     * Entry point of the application.
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the Gradebook & Utilities App!");
        while (true) { // main menu loop
            printMainMenu();
            int choice = getIntInput(1, 5);

            switch (choice) {
                case 1: addStudent(); break;
                case 2: enterGrades(); break;
                case 3: showStudents(); break;
                case 4: utilitiesMenu(); break;
                case 5:
                    System.out.println("Exiting. Goodbye!");
                    return;
            }
        }
    }

    /**
     * Prints the main menu options.
     */
    static void printMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Add student");
        System.out.println("2. Enter grades");
        System.out.println("3. Show all students");
        System.out.println("4. Utilities");
        System.out.println("5. Exit");
        System.out.print("Choose (1-5): ");
    }

    /**
     * Adds new students using a do-while loop for repeat prompt.
     */
    static void addStudent() {
        do {
            System.out.print("Enter student name: ");
            String name = scan.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name can't be empty.");
                continue;
            }
            System.out.print("Enter student ID (integer): ");
            int id = getAnyIntInput();

            Student s = new Student();
            s.name = name;
            s.id = id;
            students.add(s);

            System.out.println("Student added: " + s);

            String again;
            do {
                System.out.print("Add another student? (y/n): ");
                again = scan.nextLine().trim().toLowerCase();
            } while (!again.equals("y") && !again.equals("n"));

            if (!again.equals("y")) break;
        } while (true);
    }

    /**
     * Enter grades for student by name.
     */
    static void enterGrades() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        System.out.print("Enter student name to enter grades: ");
        String targetName = scan.nextLine().trim();
        Student target = null;
        for (Student s : students) {
            if (s.name.equals(targetName)) { // string equality
                target = s;
                break;
            }
        }
        if (target == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println("Enter 5 grades for " + target.name + ":");
        for (int i = 0; i < 5; i++) { // for loop
            System.out.printf("  Grade #%d (0-100): ", i + 1);
            double grade = getDoubleInput(0, 100);
            target.setGrade(i, grade);
        }
        System.out.println("Grades updated for " + target.name);
    }

    /**
     * Displays all students, averages and letter grades.
     */
    static void showStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("\nStudents:");
        for (Student s : students) { // for-each loop
            System.out.println(s);
        }
        System.out.print("Student names: ");
        for (Student s : students) {
            System.out.print(s.name + "  ");
        }
        System.out.println();
    }

    /**
     * Utilities submenu with operator, casting, and recursion demos.
     */
    static void utilitiesMenu() {
        while (true) {
            System.out.println("\nUtilities Menu:");
            System.out.println("1. Operator precedence demo");
            System.out.println("2. Type casting demo");
            System.out.println("3. Recursion countdown demo");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose (1-4): ");

            int opt = getIntInput(1, 4);
            switch (opt) {
                case 1: operatorDemo(); break;
                case 2: castingDemo(); break;
                case 3: recursionDemo(); break;
                case 4: return;
            }
        }
    }

    /** Shows operator precedence results. */
    static void operatorDemo() {
        int resultA = 2 + 3 * 4;
        int resultB = (2 + 3) * 4;
        System.out.println("2 + 3 * 4 = " + resultA);
        System.out.println("(2 + 3) * 4 = " + resultB);
        System.out.println("Multiplication proceeds before addition unless parentheses used (BODMAS).");
    }

    /** Demonstrates widening and narrowing type casts. */
    static void castingDemo() {
        int a = 42;
        double b = a;  // widening
        double d = 77.9;
        int e = (int)d; // narrowing/truncation
        System.out.println("int a = 42; double b = a; b = " + b);
        System.out.println("double d = 77.9; int e = (int)d; e = " + e + " (fractional part lost)");
    }

    /** Recursively counts down from n to 0, guards negative input. */
    static void recursionDemo() {
        System.out.print("Enter a non-negative integer for countdown: ");
        int n = getAnyIntInput();
        if (n < 0) {
            System.out.println("Negative value not allowed!");
            return;
        }
        System.out.print("Countdown: ");
        countdown(n);
        System.out.println("Done!");
    }

    /**
     * Recursively prints numbers from n to 0.
     * @param n starting number
     */
    static void countdown(int n) {
        System.out.print(n + " ");
        if (n == 0) return;
        countdown(n - 1);
    }

    // ===== Input helper methods =====
    /** Prompts for an integer in a range, handles invalid input. */
    static int getIntInput(int min, int max) {
        while (true) {
            try {
                String line = scan.nextLine().trim();
                int val = Integer.parseInt(line);
                if (val < min || val > max) throw new NumberFormatException();
                return val;
            } catch (Exception e) {
                System.out.print("Invalid! Enter integer (" + min + "-" + max + "): ");
            }
        }
    }

    /** Prompts for any integer, including negative, handles invalid input. */
    static int getAnyIntInput() {
        while (true) {
            try {
                String line = scan.nextLine().trim();
                return Integer.parseInt(line);
            } catch (Exception e) {
                System.out.print("Invalid! Enter an integer: ");
            }
        }
    }

    /** Prompts for a double in a range, handles invalid input. */
    static double getDoubleInput(double min, double max) {
        while (true) {
            try {
                String line = scan.nextLine().trim();
                double val = Double.parseDouble(line);
                if (val < min || val > max) throw new NumberFormatException();
                return val;
            } catch (Exception e) {
                System.out.print("Invalid! Enter a number (" + min + "-" + max + "): ");
            }
        }
    }
}
