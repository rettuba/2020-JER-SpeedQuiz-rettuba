package com.rettuba.speedquiz;

import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Question {

    private String question;
    private Boolean reponse;
    public Question(){}

    /**
     * @param question
     * @param reponse
     */
    public Question(String question, Boolean reponse){
        this.question = question;
        this.reponse = reponse;
    }


    public  String getQuestion() {
        return question;

    }

    public Boolean getReponse() {
        return reponse;

    }

}
