package com.rettuba.speedquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import java.util.ArrayList;

public class QuestionManager extends AppCompatActivity {

    Question maQuestion = new Question();

    public static int numeroAleatoire() {
        int numeroAleatoire = (int) (Math.random() * Source.listeDeQuestions().size());
        return numeroAleatoire;

    }

    /**
     * Prendre une question aléatoirement dans la liste
     */
    public static String questionAleatoire(ArrayList listeDeQuestions) {

        String questionRandom = listeDeQuestions.get(numeroAleatoire()).toString();
        return questionRandom;
    }

    public static boolean reponseAleatoire(ArrayList listeReponse) {

        return (boolean) listeReponse.get(numeroAleatoire());
    }

    /**
     * Enlever la question posée de la liste (pour éviter de poser 2 fois la même question
     **/
    public static void removeQuestionReponse(ArrayList listeDeQuestions, ArrayList listeDeReponse, String phraseARemove) {
        listeDeQuestions.remove(phraseARemove);
        listeDeReponse.remove(phraseARemove);

        System.out.println();

    }

    public String getQuestion() {
        maQuestion.setQuestion(questionAleatoire(Source.listeDeQuestions()));
        return maQuestion.getQuestion();

    }

    public boolean getReponse() {
        maQuestion.setReponse(reponseAleatoire(Source.listeDesReponses()));
        return maQuestion.getReponse();

    }
}
