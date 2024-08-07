package edu.utsa.cs3443.clockworks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import edu.utsa.cs3443.clockworks.TodayActivity;

public class MyRemindersActivity extends Activity {

    private Button buttonToday; // Button to navigate to Today's reminders
    private Button buttonThisWeek; // Button to navigate to This Week's reminders
    private ImageButton backButton; // Button to navigate back to MainActivity
    private KanbanBoard board; // KanbanBoard object to manage tasks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders); // Set the content view to the layout file

        board = (KanbanBoard) getIntent().getSerializableExtra("board"); // Get the KanbanBoard object from the Intent

        buttonToday = findViewById(R.id.button_today); // Find the Today button by its ID
        buttonThisWeek = findViewById(R.id.button_this_week); // Find the This Week button by its ID
        backButton = findViewById(R.id.button_arrow); // Find the Back button by its ID

        // Set up an OnClickListener for the Today button
        buttonToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyRemindersActivity.this, TodayActivity.class);
                intent.putExtra("board", board); // Pass the KanbanBoard object to the TodayActivity
                startActivity(intent); // Start the TodayActivity
            }
        });

        // Set up an OnClickListener for the This Week button
        buttonThisWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyRemindersActivity.this, ActivityThisWeek.class);
                intent.putExtra("board", board); // Pass the KanbanBoard object to the ActivityThisWeek
                startActivity(intent); // Start the ActivityThisWeek
            }
        });

        // Set up an OnClickListener for the Back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to MainActivity and clear the activity stack
                Intent intent = new Intent(MyRemindersActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("board", board); // Pass the KanbanBoard object to the MainActivity
                startActivity(intent); // Start the MainActivity
                finish(); // Finish the current activity
            }
        });
    }
}
