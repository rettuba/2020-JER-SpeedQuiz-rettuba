package com.rettuba.speedquiz;

import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Question extends AppCompatActivity {


    private String question;
    private int reponse;
    public Question(){};

    /**
     * @param question
     * @param reponse
     */
    public Question(String question, int reponse){
        this.question = question;
        this.reponse = reponse;
    }

    public void setQuestion(String question) {
        this.question = question;

    }

    public String getQuestion() {
        return question;

    }

    public void setReponse(int reponse) {
        this.reponse =  reponse;

    }

    public int getReponse() {
        return reponse;

    }


}
