package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button btnPlay;
    TextView sumTextView;
    TextView resultText;
    TextView scoreTextView;
    TextView timeText;
    ConstraintLayout gameLayout;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int incorrectAnswer;
    int score = 0;
    int numberOfQuestion = 0;

    public void playAgain(View view){

        score = 0;
        numberOfQuestion = 0;

        timeText.setText("30s");
        scoreTextView.setText("0/0");
        resultText.setText("Game Dimulai!");
        btnPlay.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long l) {
                timeText.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {

                btnPlay.setVisibility(View.VISIBLE);
                timeText.setText("0s");
                resultText.setText("Your score: " + Integer.toString(score) +"/"+ Integer.toString(numberOfQuestion));
            }
        }.start();

    }

    public void generateQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for (int i=0; i<4; i++){
            if (i == locationOfCorrectAnswer){
                answers.add(a+b);
            }else {
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a+b){
                    incorrectAnswer = rand.nextInt(41);
                }

                answers.add(incorrectAnswer);

            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view){
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            resultText.setText("Correct!");
            score++;
        }else {
            resultText.setText("Wrong!");
        }

        numberOfQuestion++;
        scoreTextView.setText(Integer.toString(score) +"/"+ Integer.toString(numberOfQuestion));
        generateQuestion();
    }

    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(ConstraintLayout.VISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultText = findViewById(R.id.resultText);
        scoreTextView = findViewById(R.id.scoreTextView);
        timeText = findViewById(R.id.timeText);
        btnPlay = findViewById(R.id.btnPlay);
        gameLayout = findViewById(R.id.gameLayout);

        playAgain(findViewById(R.id.btnPlay));

    }
}