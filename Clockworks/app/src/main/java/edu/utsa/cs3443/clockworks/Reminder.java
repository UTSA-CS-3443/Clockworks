package edu.utsa.cs3443.clockworks;

public class Reminder {
    private String text; // The text of the reminder
    private String time; // The time of the reminder
    private boolean isCircleFilled; // Indicates whether the reminder's circle is filled

    // Constructor to initialize the Reminder object with text and time
    public Reminder(String text, String time) {
        this.text = text;
        this.time = time;
        this.isCircleFilled = false; // Default to not filled
    }

    // Getter for the reminder text
    public String getText() {
        return text;
    }

    // Getter for the reminder time
    public String getTime() {
        return time;
    }

    // Getter to check if the circle is filled
    public boolean isCircleFilled() {
        return isCircleFilled;
    }

    // Method to toggle the circle's filled status
    public void toggleCircleFilled() {
        this.isCircleFilled = !this.isCircleFilled;
    }
}
