package edu.utsa.cs3443.clockworks;

import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Motivations {

    private ArrayList<String> quotes; // List to store quotes

    // Constructor to initialize the quotes list
    public Motivations() {
        this.quotes = new ArrayList<>();
    }

    // Getter for quotes list
    public ArrayList<String> getQuotes() {
        return quotes;
    }

    // Setter for quotes list
    public void setQuotes(ArrayList<String> quotes) {
        this.quotes = quotes;
    }

    // Method to load quotes from a CSV file in the assets folder
    public void loadQuotes(MotivationsActivity motivationsActivity) {
        AssetManager manager = motivationsActivity.getAssets(); // Get the AssetManager to access the assets folder
        Scanner scan; // Scanner to read the file
        String filename = "Quotes.csv"; // Name of the CSV file containing quotes
        InputStream file;

        try {
            file = manager.open(filename); // Open the CSV file from assets
            scan = new Scanner(file); // Initialize the scanner with the file input stream
            String line;
            String[] tokens;

            // Read and process 25 lines from the CSV file
            for (int i = 0; i < 25; i++) {
                line = scan.nextLine(); // Read the next line
                tokens = line.split(";"); // Split the line into tokens using ";" as the delimiter

                String quote = tokens[0].trim(); // Extract the quote
                String author = tokens[1].trim(); // Extract the author
                String fullQuote = quote + "\n\n -" + author; // Format the quote with the author

                quotes.add(fullQuote); // Add the formatted quote to the quotes list
            }

            scan.close(); // Close the scanner
        } catch (IOException e) {
            throw new RuntimeException(e); // Handle any IOException by throwing a RuntimeException
        }
    }
}
