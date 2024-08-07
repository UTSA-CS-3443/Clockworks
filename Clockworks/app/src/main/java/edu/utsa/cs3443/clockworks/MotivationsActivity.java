package edu.utsa.cs3443.clockworks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MotivationsActivity extends AppCompatActivity {

    private Motivations motives; // Object to manage motivational quotes
    private TextView quoteTextView; // TextView to display a motivational quote
    private KanbanBoard board; // KanbanBoard object to manage tasks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enable edge-to-edge display
        setContentView(R.layout.activity_motivations); // Set the content view to the layout file

        Random random = new Random(); // Create a Random object to select a random quote
        int quoteID = random.nextInt(25); // Generate a random index for the quote

        motives = new Motivations(); // Initialize the Motivations object
        motives.loadQuotes(this); // Load quotes from the CSV file

        quoteTextView = findViewById(R.id.quote_text_view); // Find the TextView by its ID
        quoteTextView.setText(motives.getQuotes().get(quoteID)); // Set a random quote in the TextView

        board = (KanbanBoard) getIntent().getSerializableExtra("board"); // Get the KanbanBoard object from the Intent

        // Button to navigate to TimerActivity
        Button toTimer = findViewById(R.id.toTimer);
        toTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MotivationsActivity.this, TimerActivity.class);
                intent.putExtra("board", board); // Pass the KanbanBoard object to the TimerActivity
                startActivity(intent); // Start the TimerActivity
            }
        });

        // Button to navigate to KanbanActivity
        Button toKanban = findViewById(R.id.toKanban);
        toKanban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MotivationsActivity.this, KanbanActivity.class);
                intent.putExtra("board", board); // Pass the KanbanBoard object to the KanbanActivity
                startActivity(intent); // Start the KanbanActivity
            }
        });

        // Button to navigate to MainActivity
        Button toReminders = findViewById(R.id.toReminders);
        toReminders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MotivationsActivity.this, MainActivity.class);
                intent.putExtra("board", board); // Pass the KanbanBoard object to the MainActivity
                startActivity(intent); // Start the MainActivity
            }
        });

        // Set padding for system bars (e.g., status bar and navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
