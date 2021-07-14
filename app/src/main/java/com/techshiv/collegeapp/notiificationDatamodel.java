package com.techshiv.collegeapp;

public class notiificationDatamodel {
    String NotificationMessage,NotificationTitle,datetime;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    notiificationDatamodel(){}
    public String getNotificationMessage() {
        return NotificationMessage;
    }

    public void setNotificationMessage(String notificationMessage) {
        NotificationMessage = notificationMessage;
    }

    public String getNotificationTitle() {
        return NotificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        NotificationTitle = notificationTitle;
    }

    public notiificationDatamodel(String notificationMessage, String notificationTitle,String datetime) {
        NotificationMessage = notificationMessage;
        NotificationTitle = notificationTitle;
        this.datetime=datetime;
    }
}
