package com.example.lab3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //ArrayList secretWords = new ArrayList(Arrays.asList("APPLE", "BANANA", "CHERRY"));
    ArrayList<String> secretWords ;
    char nextLetter;
    int incorrectCounter;
    int correctGuesses;
    String correctLetters;
    String shuffledWord;
    String word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        secretWords = new ArrayList<String>();
        secretWords.add("APPLE");
        secretWords.add("BANANA");
        secretWords.add("CHERRY");

        Random rand = new Random();
        int index = rand.nextInt(secretWords.size());
        word = secretWords.get(index);
        correctGuesses = 0;
        shuffledWord = "";
        ArrayList<String> splitWord = new ArrayList(Arrays.asList(word.split("")));
        Collections.shuffle(splitWord);
        for (String c : splitWord)
            shuffledWord += c;

        TextView t = (TextView) findViewById(R.id.scrambleText);
        t.setText(shuffledWord);

        nextLetter = word.charAt(correctGuesses);

        incorrectCounter = 0;

        correctLetters = "";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void buttonClicked(View v) {
        TextView u = (TextView) findViewById(R.id.userText);
        TextView l = (TextView) findViewById(R.id.correctLettersText);
        TextView i = (TextView) findViewById(R.id.introText);

        String s = u.getText().toString();
        char c = s.charAt(0);
        //compare the character to the next character in the word.
        //if they are the same, do not increment incorrect counter.
            //update the correctLettersText
        if(nextLetter == c) {
            correctLetters += c;
            correctGuesses++;
            if(!word.equals(correctLetters)) {
                nextLetter = word.charAt(correctGuesses);
            }

            l.setText(correctLetters);
        }
        //if they are not the same, increment the incorrect counter.
        else{
            incorrectCounter++;
        }
        //clear userText
        u.setText("");

        //if they guessed the full word, display a message telling them, and display how many incorrect guesses they had.
        if(word.equals(correctLetters)){
            i.setText("Congratulations! You guessed the word with " + Integer.toString(incorrectCounter) + " incorrect guesses.");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
