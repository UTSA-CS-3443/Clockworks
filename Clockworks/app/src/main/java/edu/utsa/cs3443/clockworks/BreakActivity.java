package edu.utsa.cs3443.clockworks;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BreakActivity extends AppCompatActivity {

    private TextView timerText; // TextView to display the remaining break time
    private Button startNewPomodoroButton; // Button to start a new Pomodoro session
    private Handler handler = new Handler(); // Handler for scheduling the timer updates
    private TimerModel timerModel; // Model to manage the timer state
    private KanbanBoard board; // KanbanBoard object to manage tasks

    private static final int BREAK_TIME = 300; // 5 minutes in seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break); // Set the layout for this activity

        timerModel = new TimerModel(BREAK_TIME); // Initialize the timer model with the break time

        timerText = findViewById(R.id.breakTimerText); // Find the TextView for the timer
        startNewPomodoroButton = findViewById(R.id.startNewPomodoroButton); // Find the button to start a new Pomodoro session

        timerText.setText(timerModel.formatTime()); // Set the initial timer text

        startNewPomodoroButton.setOnClickListener(v -> startNewPomodoro()); // Set click listener for the Pomodoro button

        board = (KanbanBoard) getIntent().getSerializableExtra("board"); // Retrieve the KanbanBoard object passed from the previous activity

        Button toKanban = findViewById(R.id.toKanban); // Find the button to navigate to KanbanActivity
        toKanban.setOnClickListener(new View.OnClickListener() { // Set click listener for the Kanban button
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BreakActivity.this, KanbanActivity.class); // Create intent to navigate to KanbanActivity
                intent.putExtra("board", board); // Pass the KanbanBoard object
                startActivity(intent); // Start the KanbanActivity
            }
        });

        Button toMotivation = findViewById(R.id.toMotivation); // Find the button to navigate to MotivationsActivity
        toMotivation.setOnClickListener(new View.OnClickListener() { // Set click listener for the Motivation button
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BreakActivity.this, MotivationsActivity.class); // Create intent to navigate to MotivationsActivity
                intent.putExtra("board", board); // Pass the KanbanBoard object
                startActivity(intent); // Start the MotivationsActivity
            }
        });

        Button toReminders = findViewById(R.id.toReminders); // Find the button to navigate to MainActivity
        toReminders.setOnClickListener(new View.OnClickListener() { // Set click listener for the Reminders button
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BreakActivity.this, MainActivity.class); // Create intent to navigate to MainActivity
                intent.putExtra("board", board); // Pass the KanbanBoard object
                startActivity(intent); // Start the MainActivity
            }
        });

        startBreakTimer(); // Start the break timer
    }

    // Start the break timer
    private void startBreakTimer() {
        timerModel.setRunning(true); // Set the timer to running state
        handler.post(new Runnable() { // Create a new Runnable to update the timer every second
            @Override
            public void run() {
                if (timerModel.getSeconds() > 0 && timerModel.isRunning()) { // Check if there is time left and the timer is running
                    timerText.setText(timerModel.formatTime()); // Update the timer text
                    timerModel.setSeconds(timerModel.getSeconds() - 1); // Decrease the timer by one second
                    handler.postDelayed(this, 1000); // Schedule the next update in one second
                } else if (timerModel.getSeconds() == 0) { // If the timer reaches zero
                    onBreakFinish(); // Handle the break finish
                }
            }
        });
    }

    // Handle the end of the break
    private void onBreakFinish() {
        timerModel.setRunning(false); // Stop the timer
        timerText.setText("Break finished!"); // Update the timer text to indicate the break is finished
        startNewPomodoroButton.setVisibility(View.VISIBLE); // Show the button to start a new Pomodoro session
    }

    // Start a new Pomodoro session
    private void startNewPomodoro() {
        Intent intent = new Intent(this, TimerActivity.class); // Create intent to navigate to TimerActivity
        startActivity(intent); // Start the TimerActivity
        finish(); // Finish the current activity
    }
}

