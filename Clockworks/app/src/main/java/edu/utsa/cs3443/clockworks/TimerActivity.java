package edu.utsa.cs3443.clockworks;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TimerActivity extends AppCompatActivity {

    private TextView timerText;  // Displays the timer value
    private Button startButton;  // Starts the timer
    private Button resetButton;  // Resets the timer
    private Button pauseButton;  // Pauses or resumes the timer
    private Handler handler = new Handler();  // Handles timer updates
    private TimerModel timerModel;  // Model for managing the timer
    private KanbanBoard board;  // Kanban board object passed between activities

    private static final int WORK_TIME = 1500; // Duration of the timer in seconds (25 minutes)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);  // Enable edge-to-edge layout for fullscreen display
        setContentView(R.layout.activity_timer);  // Set the content view for this activity

        // Set padding for system bars (status and navigation bars) to avoid overlapping content
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        timerModel = new TimerModel(WORK_TIME);  // Initialize the timer model with the work time

        // Find and initialize UI elements
        timerText = findViewById(R.id.timerText);
        startButton = findViewById(R.id.startButton);
        resetButton = findViewById(R.id.resetButton);
        pauseButton = findViewById(R.id.pauseButton);

        // Set the initial timer text
        timerText.setText(timerModel.formatTime());

        // Set up button click listeners
        startButton.setOnClickListener(v -> startTimer());  // Start the timer when clicked
        resetButton.setOnClickListener(v -> resetTimer());  // Reset the timer when clicked
        pauseButton.setOnClickListener(v -> pauseResumeTimer());  // Pause or resume the timer when clicked

        // Retrieve the Kanban board object from the intent
        board = (KanbanBoard) getIntent().getSerializableExtra("board");

        // Set up navigation buttons
        Button toKanban = findViewById(R.id.toKanban);
        toKanban.setOnClickListener(view -> {
            Intent intent = new Intent(TimerActivity.this, KanbanActivity.class);
            intent.putExtra("board", board);
            startActivity(intent);
        });

        Button toMotivation = findViewById(R.id.toMotivation);
        toMotivation.setOnClickListener(view -> {
            Intent intent = new Intent(TimerActivity.this, MotivationsActivity.class);
            intent.putExtra("board", board);
            startActivity(intent);
        });

        Button toReminders = findViewById(R.id.toReminders);
        toReminders.setOnClickListener(view -> {
            Intent intent = new Intent(TimerActivity.this, MainActivity.class);
            intent.putExtra("board", board);
            startActivity(intent);
        });
    }

    // Start the timer if it's not already running
    private void startTimer() {
        if (!timerModel.isRunning()) {
            timerModel.setRunning(true);
            runTimer();  // Begin updating the timer
            pauseButton.setText("Pause");  // Change button text to "Pause"
            startButton.setEnabled(false);  // Disable the start button
        }
    }

    // Pause or resume the timer based on its current state
    private void pauseResumeTimer() {
        if (timerModel.isRunning()) {
            timerModel.setRunning(false);
            handler.removeCallbacksAndMessages(null);  // Stop timer updates
            pauseButton.setText("Resume");  // Change button text to "Resume"
        } else {
            timerModel.setRunning(true);
            runTimer();  // Resume timer updates
            pauseButton.setText("Pause");  // Change button text to "Pause"
        }
    }

    // Reset the timer to its initial state
    private void resetTimer() {
        timerModel.reset();  // Reset timer model
        timerText.setText(timerModel.formatTime());  // Update the displayed time
        handler.removeCallbacksAndMessages(null);  // Stop timer updates
        pauseButton.setText("Pause");  // Change button text to "Pause"
        startButton.setEnabled(true);  // Enable the start button
    }

    // Run the timer, updating the display every second
    private void runTimer() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (timerModel.getSeconds() > 0 && timerModel.isRunning()) {
                    timerText.setText(timerModel.formatTime());  // Update the displayed time
                    timerModel.setSeconds(timerModel.getSeconds() - 1);  // Decrement timer value
                    handler.postDelayed(this, 1000);  // Schedule next update in 1 second
                } else if (timerModel.getSeconds() == 0) {
                    onTimerFinish();  // Timer has finished, handle completion
                }
            }
        });
    }

    // Handle actions to be taken when the timer finishes
    private void onTimerFinish() {
        timerModel.setRunning(false);  // Stop the timer
        startBreakActivity();  // Start the break activity
    }

    // Start the activity for the break period
    private void startBreakActivity() {
        Intent intent = new Intent(this, BreakActivity.class);
        startActivity(intent);  // Launch the break activity
        finish();  // Close this activity
    }
}
