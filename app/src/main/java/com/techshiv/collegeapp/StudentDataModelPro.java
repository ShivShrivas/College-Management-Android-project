package com.techshiv.collegeapp;

public class StudentDataModelPro {
    String StudentEnrollment,StudentFatherName,StudentMail,StudentName;
    StudentDataModelPro(){}
    public String getStudentEnrollment() {
        return StudentEnrollment;
    }

    public void setStudentEnrollment(String studentEnrollment) {
        StudentEnrollment = studentEnrollment;
    }

    public String getStudentFatherName() {
        return StudentFatherName;
    }

    public void setStudentFatherName(String studentFatherName) {
        StudentFatherName = studentFatherName;
    }

    public String getStudentMail() {
        return StudentMail;
    }

    public void setStudentMail(String studentMail) {
        StudentMail = studentMail;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public StudentDataModelPro(String studentEnrollment, String studentFatherName, String studentMail, String studentName) {
        StudentEnrollment = studentEnrollment;
        StudentFatherName = studentFatherName;
        StudentMail = studentMail;
        StudentName = studentName;
    }
}
