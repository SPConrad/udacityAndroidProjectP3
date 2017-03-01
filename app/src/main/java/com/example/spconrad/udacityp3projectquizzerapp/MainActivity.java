package com.example.spconrad.udacityp3projectquizzerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private enum QuestionType {
        radio,
        checkbox,
        text
    }

    private QuestionType questionType = QuestionType.radio;

    private int score = 0;

    int correct = 0;

    private int numberOfRadioQuestions = 4;
    private int numberOfCheckboxQuestions = 2;
    private int numberOfTextQuestions = 1;

    private int numberOfQuestions = 0;

    private String s_correctAnswer;

    private String selectedAnswer = "";

    private Question[] questions;// = new Question[numberOfQuestions];

    private TextView questionTextView;

    private RadioButton[] radioAnswerTextViews = new RadioButton[4];

    private CheckBox[] checkboxAnswerTextViews = new CheckBox[4];

    private TextView enterTextTextView;


    private int currentQuestion = 0;

    private Button submitButton;

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.radio_layout);

        numberOfQuestions = numberOfRadioQuestions + numberOfCheckboxQuestions + numberOfTextQuestions;

        questions = new Question[numberOfQuestions];

        submitButton = (Button) findViewById(R.id.submit_button);

        questionTextView = (TextView) findViewById(R.id.question_textView);

        enterTextTextView = (TextView) findViewById(R.id.enter_text_textView);

        radioAnswerTextViews[0] = (RadioButton) findViewById(R.id.radio_answer_1_textView);
        radioAnswerTextViews[1] = (RadioButton) findViewById(R.id.radio_answer_2_textView);
        radioAnswerTextViews[2] = (RadioButton) findViewById(R.id.radio_answer_3_textView);
        radioAnswerTextViews[3] = (RadioButton) findViewById(R.id.radio_answer_4_textView);

        checkboxAnswerTextViews[0] = (CheckBox) findViewById(R.id.checkbox_answer_1_textView);
        checkboxAnswerTextViews[1] = (CheckBox) findViewById(R.id.checkbox_answer_2_textView);
        checkboxAnswerTextViews[2] = (CheckBox) findViewById(R.id.checkbox_answer_3_textView);
        checkboxAnswerTextViews[3] = (CheckBox) findViewById(R.id.checkbox_answer_4_textView);

        for (int i = 0; i < numberOfQuestions - numberOfTextQuestions; i++) {
            String identifier = getPackageName() + ":string/q" + (i + 1);
            int id = getResources().getIdentifier(identifier, null, null);

            String tempQuestion = getString(id);

            identifier = getPackageName() + ":string/q" + (i + 1) + "_ra";
            id = getResources().getIdentifier(identifier, null, null);

            String tempCorrectAnswer = getString(id, null, null);

            String[] tempAnswers = new String[4];

            for (int x = 0; x < 4; x++) {
                identifier = getPackageName() + ":string/q" + (i + 1) + "_a_" + (x + 1);

                id = getResources().getIdentifier(identifier, null, null);
                tempAnswers[x] = getString(id);
            }
            Question newQuestion = new Question(tempQuestion, tempAnswers, tempCorrectAnswer);
            questions[i] = newQuestion;
        }

        String tempQuestion = getString(R.string.q7);
        String tempAnswer = getString(R.string.q7_ra);
        String[] tempAnswerArray = tempAnswer.split(",");
        String tempCorrectAnswer = getString(R.string.q7_ra);

        Question newQuestion = new Question(tempQuestion, tempAnswerArray, tempCorrectAnswer);
        questions[6] = newQuestion;

        setUpNextQuestion();
    }

    /*private void increaseScore() {
       score += 100;
    }*/

    public void setAnswer(View view) {
        selectedAnswer = (String) (view).getTag();
    }

    public void submitAnswer(View view) {
        Boolean answeredCorrectly = false;
        if (questionType == QuestionType.radio) {
            if (selectedAnswer.equals(s_correctAnswer)) {
                answeredCorrectly = true;
            }
            radioGroup.clearCheck();
            selectedAnswer = "";
        } else if (questionType == QuestionType.checkbox) {
            String answer = "";
            for (int i = 0; i < 4; i++) {
                if (checkboxAnswerTextViews[i].isChecked()) {
                    answer = answer + "" + (i + 1);
                }
            }
            String validAnswer = questions[currentQuestion].getCorrectAnswer();
            if (answer.equals(validAnswer)) {
                answeredCorrectly = true;
            }

            for (int i = 0; i < 4; i++) {
                if (checkboxAnswerTextViews[i].isChecked()) {
                    checkboxAnswerTextViews[i].setChecked(false);
                }
            }
        } else if (questionType == QuestionType.text) {
            String enteredAnswer = enterTextTextView.getText().toString();
            String[] validAnswers = questions[currentQuestion].getAnswers();
            enteredAnswer = enteredAnswer.toLowerCase();
            for (int i = 0; i < validAnswers.length; i++) {
                if (enteredAnswer.equals(validAnswers[i])) {
                    answeredCorrectly = true;
                    break;
                }
            }
        }

        if (answeredCorrectly == true) {
            correct += 1;
            //increaseScore();
        }
        currentQuestion += 1;
        if (currentQuestion < (numberOfQuestions)) {
            setUpNextQuestion();
        } else {
            completedQuiz();
        }
    }
    
    private void completedQuiz(){
        Toast.makeText(this, "Congratulations! You answered " + correct + " out of " + numberOfQuestions, Toast.LENGTH_LONG).show();
        submitButton.setEnabled(false);
    }

    private void setUpNextQuestion() {
        if (currentQuestion < 4) {
            changeVisibility(R.id.radio_layout, true);
            changeVisibility(R.id.checkbox_layout, false);
            changeVisibility(R.id.enter_text_textView, false);
            questionType = QuestionType.radio;
            assignTextboxes(radioAnswerTextViews);
        } else if (currentQuestion == 4 || currentQuestion == 5) {
            changeVisibility(R.id.checkbox_layout, true);
            changeVisibility(R.id.radio_layout, false);
            changeVisibility(R.id.enter_text_textView, false);
            questionType = QuestionType.checkbox;
            assignTextboxes(checkboxAnswerTextViews);
        } else {
            changeVisibility(R.id.enter_text_textView, true);
            changeVisibility(R.id.radio_layout, false);
            changeVisibility(R.id.checkbox_layout, false);
            questionType = QuestionType.text;
        }


        assignQuestionBox();
        s_correctAnswer = questions[currentQuestion].getCorrectAnswer();

    }

    private void assignQuestionBox() {
        questionTextView.setText(questions[currentQuestion].getQuestion());
    }

    private void assignTextboxes(TextView[] textViews) {
        for (int i = 0; i < 4; i++) {
            textViews[i].setText(questions[currentQuestion].getAnswer(i));
        }
    }

    private void changeVisibility(int id, boolean visible) {
        View _layout = (View) findViewById(id);
        if (visible == true) {
            _layout.setVisibility(View.VISIBLE);
        } else if (visible == false) {
            _layout.setVisibility(View.GONE);
        }
    }
}
