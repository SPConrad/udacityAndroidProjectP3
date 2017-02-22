package com.example.spconrad.udacityp3projectquizzerapp;

/**
 * Created by Sean on 2/22/2017.
 */

public class Question {
    private String _question = "";

    private String[] _answers = new String[4];

    public Question() {

    }

    public Question(String question, String[] answers){
        setQuestion(question);
        setAnswers(answers);
    }

    public String getQuestion(){
        return _question;
    }

    public void setQuestion(String question){
        _question = question;
    }

    public String[] getAnswers(){
        return _answers;
    }

    public void setAnswers(String[] answers){
        _answers = answers;
    }
}
