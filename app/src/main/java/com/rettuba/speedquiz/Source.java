package com.rettuba.speedquiz;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class Source extends AppCompatActivity {

    public static ArrayList listeDeQuestions() {

        ArrayList<String> Question = new ArrayList<String>();

        Question.add("La racine de 145 est un nombre entier");                     /*FAUX*/
        Question.add("La suisse a 23 cantons");                                    /*FAUX*/
        Question.add("La suisse a plus de 8 millions d'habitants (année : 2020)"); /*VRAI*/
        Question.add("La Russie est le plus grand pays au monde");                 /*Vrai*/
        Question.add("L'Afrique est un pays");                                     /*Faux*/
        Question.add("La Lune est un satellite naturel");                          /*Vrai*/
        Question.add("La terre a moins d'un milliard d'années");                   /*FAUX*/

        return Question;
    }

    public static ArrayList listeDesReponses() {

        ArrayList<Boolean> Reponse = new ArrayList<Boolean>();

        Reponse.add(false);
        Reponse.add(false);
        Reponse.add(true);
        Reponse.add(true);
        Reponse.add(false);
        Reponse.add(true);
        Reponse.add(false);


        return Reponse;
    }

}