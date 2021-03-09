package com.rettuba.speedquiz;

import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Question {

    private String question;
    private int reponse;
    public Question(){}


    /**
     * Créer un objet Question
     * @param cursor
     */
    public Question(Cursor cursor){
        question = cursor.getString(cursor.getColumnIndex("question"));
        reponse = cursor.getInt(cursor.getColumnIndex("reponse"));
    }

    /**
     * @return une question
     */
    public  String getQuestion() {
        return question;

    }

    /**
     * @return la reponse de la question
     */
    public int getReponse() {
        return reponse;

    }

}
