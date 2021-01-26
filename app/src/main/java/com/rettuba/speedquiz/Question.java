package com.rettuba.speedquiz;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Question extends AppCompatActivity {


    private String question;
    private boolean reponse;

    public void setQuestion(String question) {
        this.question = question;

    }

    public String getQuestion() {
        return question;

    }

    public void setReponse(boolean reponse) {
        this.reponse =  reponse;

    }

    public boolean getReponse() {
        return reponse;

    }


}
