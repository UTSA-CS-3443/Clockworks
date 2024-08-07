package edu.utsa.cs3443.clockworks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class KanbanActivity extends AppCompatActivity {

    private final int[] BUTTON_IDS = {R.id.task1, R.id.task2, R.id.task3, R.id.task4, R.id.task5, R.id.task6, R.id.task7, R.id.task8, R.id.task9}; // Array of button IDs for task buttons
    private KanbanBoard board; // KanbanBoard object to manage tasks
    private Button buttons[] = new Button[9]; // Array to hold the task buttons

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enable edge-to-edge display
        setContentView(R.layout.activity_kanban); // Set the layout for this activity

        board = (KanbanBoard) getIntent().getSerializableExtra("board"); // Retrieve the KanbanBoard object passed from the previous activity
        if (board == null) {
            board = new KanbanBoard(); // Initialize a new KanbanBoard if none was passed
        }

        Button finishTask = findViewById(R.id.toggleFinishOverlay); // Find the button to finish the current task
        Button addTask = findViewById(R.id.toggleAddOverlay); // Find the button to add a new task

        // Iterate through the task queue and initialize the corresponding buttons
        for(int i = 0; i < board.getTaskQueue().size(); i++) {
            Button button = findViewById(BUTTON_IDS[i]); // Find the button by its ID
            button.setVisibility(View.VISIBLE); // Make the button visible
            String movedTask = board.getTaskQueue().get(i); // Get the task from the task queue
            button.setText(movedTask); // Set the button text to the task
            button.setOnClickListener(new View.OnClickListener() { // Set a click listener for the button
                @Override
                public void onClick(View view) {
                    board.moveToCurrentTask(movedTask); // Move the task to the current task
                    Intent intent = new Intent(KanbanActivity.this, KanbanActivity.class); // Create intent to reload the KanbanActivity
                    intent.putExtra("board", board); // Pass the updated KanbanBoard object
                    startActivity(intent); // Start the KanbanActivity
                }
            });
            buttons[i] = button; // Store the button in the array
        }

        addTask.setOnClickListener(new View.OnClickListener() { // Set a click listener for the add task button
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KanbanActivity.this, AddTaskActivity.class); // Create intent to navigate to AddTaskActivity
                intent.putExtra("board", board); // Pass the KanbanBoard object
                startActivity(intent); // Start the AddTaskActivity
            }
        });

        if (board.getCurrentTask() != null) { // If there is a current task
            finishTask.setVisibility(View.VISIBLE); // Make the finish task button visible
            finishTask.setText(board.getCurrentTask()); // Set the button text to the current task
            finishTask.setOnClickListener(new View.OnClickListener() { // Set a click listener for the finish task button
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(KanbanActivity.this, FinishTaskActivity.class); // Create intent to navigate to FinishTaskActivity
                    intent.putExtra("board", board); // Pass the KanbanBoard object
                    startActivity(intent); // Start the FinishTaskActivity
                }
            });
        }

        Button toTimer = findViewById(R.id.toTimer); // Find the button to navigate to TimerActivity
        toTimer.setOnClickListener(new View.OnClickListener() { // Set a click listener for the Timer button
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KanbanActivity.this, TimerActivity.class); // Create intent to navigate to TimerActivity
                intent.putExtra("board", board); // Pass the KanbanBoard object
                startActivity(intent); // Start the TimerActivity
            }
        });

        Button toReminders = findViewById(R.id.toReminders); // Find the button to navigate to MainActivity
        toReminders.setOnClickListener(new View.OnClickListener() { // Set a click listener for the Reminders button
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KanbanActivity.this, MainActivity.class); // Create intent to navigate to MainActivity
                intent.putExtra("board", board); // Pass the KanbanBoard object
                startActivity(intent); // Start the MainActivity
            }
        });

        Button toMotivation = findViewById(R.id.toMotivation); // Find the button to navigate to MotivationsActivity
        toMotivation.setOnClickListener(new View.OnClickListener() { // Set a click listener for the Motivation button
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KanbanActivity.this, MotivationsActivity.class); // Create intent to navigate to MotivationsActivity
                intent.putExtra("board", board); // Pass the KanbanBoard object
                startActivity(intent); // Start the MotivationsActivity
            }
        });
    }
}
