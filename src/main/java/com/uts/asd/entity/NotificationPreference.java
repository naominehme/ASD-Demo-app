package com.uts.asd.entity;

public class NotificationPreference {

    // Declare private variables
    private String customerID;
    private boolean notificationsEnabled;
    private boolean soundEnabled;

    public NotificationPreference(String customerID, boolean notificationsEnabled, boolean soundEnabled) {
        this.customerID = customerID;
        this.notificationsEnabled = notificationsEnabled;
        this.soundEnabled = soundEnabled;
    }

    public NotificationPreference() {
        // Set these as default values
        this.notificationsEnabled = true;
        this.soundEnabled = true;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public boolean isNotificationsEnabled() {
        return notificationsEnabled;
    }

    public void setNotificationsEnabled(boolean notificationsEnabled) {
        this.notificationsEnabled = notificationsEnabled;
    }

    public boolean isSoundEnabled() {
        return soundEnabled;
    }

    public void setSoundEnabled(boolean soundEnabled) {
        this.soundEnabled = soundEnabled;
    }

    @Override
    public String toString() {
        return "NotificationPreference{" +
                "customerID='" + customerID + '\'' +
                ", notificationsEnabled=" + notificationsEnabled +
                ", soundEnabled=" + soundEnabled +
                '}';
    }
}
