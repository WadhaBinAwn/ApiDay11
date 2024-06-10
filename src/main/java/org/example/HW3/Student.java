package org.example.HW3;

public class Student extends Person implements ComparableById {
    private int studentId;
    private String course;

    public Student(String name, int age, String address, int studentId, String course) {
        super(name, age, address);
        setStudentId(studentId);
        this.course = course;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        if (studentId <= 0) {
            throw new IllegalArgumentException("Invalid student ID");
        }
        this.studentId = studentId;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String getDetails() {
        return super.getDetails()  +" - " + "  Student Details:  " + "Student ID: " + studentId  +" - " + "Course: " + course;
    }


    @Override
    public boolean compareById(int id) {
        return this.getStudentId() == id;
    }

    public String toString() {
        return getDetails();
    }


}
