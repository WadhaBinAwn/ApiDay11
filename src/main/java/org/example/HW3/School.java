package org.example.HW3;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class School {
    private ArrayList<Student> students;

    public School() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int studentId) {
        students.removeIf(student -> student.getStudentId() == studentId);
    }

    public void displayStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void sortStudentsByName() {
        Collections.sort(students, Comparator.comparing(Student::getName));
    }

    public Student findStudentById(int studentId) {
        int index = Collections.binarySearch(students, new Student("", 0, "", studentId, ""), Comparator.comparingInt(Student::getStudentId));
        if (index >= 0) {
            return students.get(index);
        } else {
            return null;
        }
    }

    public void writeToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Student student : students) {
                writer.println(student.getDetails());
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void readFromFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] keyValue = line.split(": ", 2); // Split into key and value
                    if (keyValue.length == 2) {
                        // Your existing code to handle key-value pairs
                    } else {
                        System.out.println("Invalid format in line: " + line);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }



}
