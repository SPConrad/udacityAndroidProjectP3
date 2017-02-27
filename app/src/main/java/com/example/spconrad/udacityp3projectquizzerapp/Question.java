package com.example.spconrad.udacityp3projectquizzerapp;

/**
 * Created by Sean on 2/22/2017.
 */

public class Question {
    private String _question = "";

    private String[] _answers = new String[4];

    private String _correctAnswer = "";

    public Question() {

    }

    public Question(String question, String[] answers, String correctAnswer){
        setQuestion(question);
        setAnswers(answers);
        setCorrectAnswer(correctAnswer);
    }

    public String getCorrectAnswer() {
        return _correctAnswer;
    }

    private void setCorrectAnswer(String correctAnswer) {
        _correctAnswer = correctAnswer;
    }

    public String getQuestion(){
        return _question;
    }

    private void setQuestion(String question){
        _question = question;
    }

    public String[] getAnswers(){
        return _answers;
    }

    public String getAnswer(int answer){
        return _answers[answer];
    }

    private void setAnswers(String[] answers){
        _answers = answers;
    }
}
