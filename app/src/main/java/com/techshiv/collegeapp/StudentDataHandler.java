package com.techshiv.collegeapp;

public class StudentDataHandler {
    String StudentName,StudentMail,StudentFatherName,StudentEnrollment;
    StudentDataHandler(){}
    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getStudentMail() {
        return StudentMail;
    }

    public void setStudentMail(String studentMail) {
        StudentMail = studentMail;
    }

    public String getStudentFatherName() {
        return StudentFatherName;
    }

    public void setStudentFatherName(String studentFatherName) {
        StudentFatherName = studentFatherName;
    }

    public String getStudentEnrollment() {
        return StudentEnrollment;
    }

    public void setStudentEnrollment(String studentEnrollment) {
        StudentEnrollment = studentEnrollment;
    }

    public StudentDataHandler(String studentName, String studentMail, String studentFatherName, String studentEnrollment) {
        StudentName = studentName;
        StudentMail = studentMail;
        StudentFatherName = studentFatherName;
        StudentEnrollment = studentEnrollment;
    }
}
