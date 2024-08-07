package edu.utsa.cs3443.clockworks;

public class TimerModel {

    private int seconds;  // Current time remaining in seconds
    private int originalTime;  // Initial time duration in seconds
    private boolean isRunning;  // Indicates whether the timer is currently running

    // Constructor to initialize the timer with a specified duration
    public TimerModel(int seconds) {
        this.seconds = seconds;
        this.originalTime = seconds;  // Store the initial time
        this.isRunning = false;  // Timer starts in a stopped state
    }

    // Getter for the current time remaining
    public int getSeconds() {
        return seconds;
    }

    // Setter for the current time remaining
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    // Getter for the original time duration
    public int getOriginalTime() {
        return originalTime;
    }

    // Getter to check if the timer is running
    public boolean isRunning() {
        return isRunning;
    }

    // Setter to update the running state of the timer
    public void setRunning(boolean running) {
        isRunning = running;
    }

    // Resets the timer to its initial state
    public void reset() {
        this.seconds = this.originalTime;  // Reset to the original time
        this.isRunning = false;  // Stop the timer
    }

    // Formats the remaining time as a string in MM:SS format
    public String formatTime() {
        int min = seconds / 60;  // Calculate minutes
        int secs = seconds % 60;  // Calculate seconds
        return String.format("%02d:%02d", min, secs);  // Return formatted time string
    }
}
