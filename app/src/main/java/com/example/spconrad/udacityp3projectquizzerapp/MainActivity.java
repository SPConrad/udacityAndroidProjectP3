package com.example.spconrad.udacityp3projectquizzerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    int numberOfQuestions = 2;
    Question[] questions = new Question[numberOfQuestions];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < numberOfQuestions; i++){
            String tempQuestion = getString(R.string.q1);
            String[] tempAnswers = new String[] {
                    getString(R.string.q1_wa_1),
                    getString(R.string.q1_wa_2),
                    getString(R.string.q1_wa_3),
                    getString(R.string.q1_ra),
            };
            Question newQuestion = new Question();
        }

    }
}
