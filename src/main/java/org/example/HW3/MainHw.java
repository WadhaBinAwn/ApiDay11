package org.example.HW3;

public class MainHw {
    public static void main(String[] args) {

        Person person1 = new Person("Ali", 25, "123 Street");
        Person person2 = new Person("Wadha", 30, "456 Avenue");

        System.out.println("Person 1: " + person1);
        System.out.println("Person 2: " + person2);


        Student student1 = new Student("Rand", 20, "700 Road", 1000, " Software Engineering ");
        System.out.println("Student 1 Details: " + student1.getDetails());

        Student student2 = new Student("Fatimah", 25, "789 Road", 1001, "Computer Science");
        System.out.println("Student 2 Details: " + student2.getDetails());


        School school = new School();
        school.addStudent(student1);
        school.addStudent(student2);
        school.displayStudents();


        school.writeToFile("students.txt");
        School school2 = new School();
        school2.readFromFile("students.txt");
        school2.displayStudents();
    }
}
