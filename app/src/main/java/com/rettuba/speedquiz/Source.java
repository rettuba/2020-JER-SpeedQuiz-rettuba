package com.rettuba.speedquiz;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class Source {

    public static ArrayList<Question> listeDeQuestions(ArrayList<Question> liste) {

        liste.add(new Question("La racine de 145 est un nombre entier",false));                     /*FAUX*/
        liste.add(new Question("La suisse a 23 cantons",false));                                    /*FAUX*/
        liste.add(new Question("La suisse a plus de 8 millions d'habitants (année : 2020)",true)); /*VRAI*/
        liste.add(new Question("La Russie est le plus grand pays au monde",true));                 /*Vrai*/
        liste.add(new Question("L'Afrique est un pays",false));                                     /*Faux*/
        liste.add(new Question("La Lune est un satellite naturel",true));                          /*Vrai*/
        liste.add(new Question("La terre a moins d'un milliard d'années",false));                   /*FAUX*/

        return liste;
    }


}