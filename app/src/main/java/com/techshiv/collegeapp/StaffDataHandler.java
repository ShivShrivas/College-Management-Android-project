package com.techshiv.collegeapp;


public class StaffDataHandler{
    String StaffName,StaffPhone,Staffmail;
    StaffDataHandler(){}
    public String getStaffName() {
        return StaffName;
    }

    public void setStaffName(String staffName) {
        StaffName = staffName;
    }

    public String getStaffPhone() {
        return StaffPhone;
    }

    public void setStaffPhone(String staffPhone) {
        StaffPhone = staffPhone;
    }

    public String getStaffmail() {
        return Staffmail;
    }

    public void setStaffmail(String staffmail) {
        Staffmail = staffmail;
    }

    public StaffDataHandler(String staffName, String staffPhone, String staffmail) {
        StaffName = staffName;
        StaffPhone = staffPhone;
        Staffmail = staffmail;
    }
}
