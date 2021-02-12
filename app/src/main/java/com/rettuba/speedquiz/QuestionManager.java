package com.rettuba.speedquiz;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionManager {

    private ArrayList<Question> questionList = new ArrayList<>();

    public QuestionManager(){
        questionList = Source.listeDeQuestions(questionList);
    }

    public ArrayList<Question> getQuestionList(){

        return questionList;
    }

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