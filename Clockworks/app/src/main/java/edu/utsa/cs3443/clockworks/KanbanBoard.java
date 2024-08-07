package edu.utsa.cs3443.clockworks;

import java.io.Serializable;
import java.util.LinkedList;

public class KanbanBoard implements Serializable {
    private LinkedList<String> taskQueue; // LinkedList to hold tasks
    private String currentTask; // The current task being worked on
    private final int MAX_SIZE = 9; // Maximum number of tasks allowed in the queue

    // Constructor to initialize the task queue and current task
    public KanbanBoard() {
        this.taskQueue = new LinkedList<>();
        this.currentTask = null;
    }

    // Setter for the current task
    public void setCurrentTask(String currentTask) {
        this.currentTask = currentTask;
    }

    // Getter for the current task
    public String getCurrentTask() {
        return currentTask;
    }

    // Setter for the task queue
    public void setTaskQueue(LinkedList<String> taskQueue) {
        this.taskQueue = taskQueue;
    }

    // Getter for the task queue
    public LinkedList<String> getTaskQueue() {
        return taskQueue;
    }

    // Method to add a task to the queue
    public void addTask(String task) {
        if (!isQueueFull()) { // Check if the queue is not full
            taskQueue.add(task); // Add the task to the queue
            if (currentTask == null) { // If there is no current task
                currentTask = taskQueue.poll(); // Set the current task to the first task in the queue
            }
        }
    }

    // Method to finish the current task and move to the next task
    public void finishCurrentTask() {
        if (!taskQueue.isEmpty()) { // Check if the queue is not empty
            currentTask = taskQueue.poll(); // Set the current task to the next task in the queue
        } else {
            currentTask = null; // If the queue is empty, set the current task to null
        }
    }

    // Method to move a task from the queue to the current task
    public void moveToCurrentTask(String task) {
        if (currentTask != null) { // Check if there is a current task
            taskQueue.addFirst(currentTask); // Add the current task back to the front of the queue
        }
        taskQueue.remove(task); // Remove the specified task from the queue
        currentTask = task; // Set the specified task as the current task
    }

    // Method to check if the task queue is full
    public boolean isQueueFull() {
        return taskQueue.size() >= MAX_SIZE; // Return true if the queue size is greater than or equal to the maximum size
    }
}
