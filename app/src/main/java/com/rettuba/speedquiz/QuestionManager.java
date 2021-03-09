package com.rettuba.speedquiz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionManager {

    private ArrayList<Question> questionList = new ArrayList<>(); // Liste d'objet Question

    public QuestionManager(Context context){
        questionList = initQuestionList(context);
    }

    private ArrayList<Question> initQuestionList(Context context){
        ArrayList<Question> listQuestion = new ArrayList<>();
        SpeedQuizSQLiteOpenHelper helper = new SpeedQuizSQLiteOpenHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(true,"quiz",new String[]{"idQuiz","question","reponse"},null,null,null,null,"idquiz",null);
        while(cursor.moveToNext()){
            listQuestion.add(new Question(cursor));
        }
        cursor.close();
        db.close();
        return listQuestion;
    }

    /**
     * @return une liste de question
     */
    public ArrayList<Question> getQuestionList(){

        return questionList;
    }

    /**
     * @param questionList
     * @return un nombre aléatoire
     */
    public int numeroAleatoire(ArrayList<Question> questionList) {

        Random random = new Random();
        return random.nextInt(questionList.size());
    }

    /**
     * Prendre une question aléatoirement dans la liste
     */
    public Question questionAleatoire() {

        int randomIndex = numeroAleatoire(questionList);
        Question question = questionList.get(randomIndex);

        questionList.remove(randomIndex);


        return question;

    }

    /**
     * Vérifier si la liste est vide
     * @param questionList liste de questions
     * @return vrai si la liste est vide sinon faux
     */
    public boolean isLastQuestion(ArrayList<Question> questionList){
        return questionList.isEmpty();

    }

}