package edu.utsa.cs3443.clockworks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.utsa.cs3443.clockworks.MyRemindersActivity;
import edu.utsa.cs3443.clockworks.NewReminderActivity;
import edu.utsa.cs3443.clockworks.Reminder;
import edu.utsa.cs3443.clockworks.ReminderUtils;
import edu.utsa.cs3443.clockworks.TodayActivity;

public class MainActivity extends Activity {

    private Button buttonPlus; // Button to add a new reminder
    private Button buttonMyReminders; // Button to view my reminders
    private KanbanBoard board; // Kanban board object to hold tasks

    private static final int NEW_REMINDER_REQUEST_CODE = 1; // Request code for new reminder activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Set the layout for this activity

        // Clear reminders for testing
        ReminderUtils.clearReminders(this); // Utility method to clear reminders

        board = (KanbanBoard) getIntent().getSerializableExtra("board"); // Get the Kanban board from the intent

        buttonPlus = findViewById(R.id.button_plus); // Initialize button to add a new reminder
        buttonMyReminders = findViewById(R.id.button_my_reminders); // Initialize button to view my reminders

        // Set click listener for the button to add a new reminder
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewReminderActivity.class); // Create an intent to start NewReminderActivity
                intent.putExtra("board", board); // Pass the Kanban board to the new activity
                startActivityForResult(intent, NEW_REMINDER_REQUEST_CODE); // Start the activity and expect a result
            }
        });

        // Set click listener for the button to view my reminders
        buttonMyReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyRemindersActivity.class); // Create an intent to start MyRemindersActivity
                intent.putExtra("board", board); // Pass the Kanban board to the new activity
                startActivity(intent); // Start the activity
            }
        });

        // Button to navigate to the TimerActivity
        Button toTimer = findViewById(R.id.toTimer);
        toTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TimerActivity.class); // Create an intent to start TimerActivity
                intent.putExtra("board", board); // Pass the Kanban board to the new activity
                startActivity(intent); // Start the activity
            }
        });

        // Button to navigate to the KanbanActivity
        Button toKanban = findViewById(R.id.toKanban);
        toKanban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, KanbanActivity.class); // Create an intent to start KanbanActivity
                intent.putExtra("board", board); // Pass the Kanban board to the new activity
                startActivity(intent); // Start the activity
            }
        });

        // Button to navigate to the MotivationsActivity
        Button toMotivation = findViewById(R.id.toMotivation);
        toMotivation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MotivationsActivity.class); // Create an intent to start MotivationsActivity
                intent.putExtra("board", board); // Pass the Kanban board to the new activity
                startActivity(intent); // Start the activity
            }
        });
    }

    // Handle the result from the NewReminderActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_REMINDER_REQUEST_CODE && resultCode == Activity.RESULT_OK) { // Check if the result is from NewReminderActivity and is OK
            String reminderText = data.getStringExtra("reminder_text"); // Get the reminder text from the result
            String reminderTime = data.getStringExtra("reminder_time"); // Get the reminder time from the result

            ReminderUtils.saveReminder(this, new Reminder(reminderText, reminderTime)); // Save the new reminder using a utility method

            // Update TodayActivity with new reminder
            Intent intent = new Intent(MainActivity.this, TodayActivity.class); // Create an intent to start TodayActivity
            startActivity(intent); // Start the activity
        }
    }
}