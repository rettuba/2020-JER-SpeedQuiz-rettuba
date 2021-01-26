package com.rettuba.speedquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /**
     * Boutons centraux
     **/
    private MaterialButton BT_Menu;
    private MaterialButton BT_Rejouer;
    /**
     * Boutons de jeux
     **/
    private MaterialButton BT_Joueur1;
    private MaterialButton BT_Joueur2;
    /**
     * Compteurs
     **/
    private TextView Compteur_Joueur1;
    private TextView Compteur_Joueur2;
    /**
     * Noms
     **/
    private TextView Nom_Joueur1;
    private TextView Nom_Joueur2;
    /**
     * Questions
     **/
    private TextView Questions_Joueur1;
    private TextView Questions_Joueur2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**Boutons centrals**/
        BT_Menu = findViewById(R.id.bt_menu);
        BT_Rejouer = findViewById(R.id.bt_rejouer);
        /**Boutons de jeux**/
        BT_Joueur1 = findViewById(R.id.bt_joueur1);
        BT_Joueur2 = findViewById(R.id.bt_joueur2);
        /**Compteurs**/
        Compteur_Joueur1 = findViewById(R.id.compteur_joueur1);
        Compteur_Joueur2 = findViewById(R.id.compteur_joueur2);
        /**Noms**/
        Nom_Joueur1 = findViewById(R.id.nom_joueur1);
        Nom_Joueur2 = findViewById(R.id.nom_joueur2);
        /**Questions**/
        Questions_Joueur1 = findViewById(R.id.questions_joueur1);
        Questions_Joueur2 = findViewById(R.id.questions_joueur2);

        /**Rendre les deux boutons "Menu" et "Rejouer" invisible**/
        BT_Menu.setVisibility(View.GONE);
        BT_Rejouer.setVisibility(View.GONE);


        /**Récupérer le nom des joueurs**/
        Intent resultActivity = getIntent();
        String nom_joueur1 = resultActivity.getStringExtra("nom_joueur1");
        String nom_joueur2 = resultActivity.getStringExtra("nom_joueur2");

        /**Afficher le nom des joueurs**/
        Nom_Joueur1.setText(nom_joueur1);
        Nom_Joueur2.setText(nom_joueur2);

        /**Lors du clique sur le bouton d'un des deux joueurs, rendre l'autre grisé**/
        /*BT_Joueur1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BT_Joueur2.setEnabled(false);
                BT_Joueur2.setBackgroundColor(Color.GRAY);
            }
        });

        BT_Joueur2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BT_Joueur1.setEnabled(false);
                BT_Joueur1.setBackgroundColor(Color.GRAY);
            }
        });*/

    QuestionManager maQuestion = new QuestionManager();

        ArrayList question = Source.listeDeQuestions();
        ArrayList reponse = Source.listeDesReponses();

        BT_Joueur1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (question.size() == 0) {
                    Questions_Joueur1.setText("Fini ! Il est bien mon jeu hein :)");
                } else {
                    Questions_Joueur1.setText(maQuestion.getQuestion());
                    QuestionManager.removeQuestionReponse(question,reponse,Questions_Joueur1.getText().toString());
                }
                Questions_Joueur2.setText(Questions_Joueur1.getText());
            }
        });

        BT_Joueur2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (question.size() == 0) {
                    Questions_Joueur1.setText("Fini ! Il est bien mon jeu hein :)");
                } else {
                    Questions_Joueur1.setText(maQuestion.getQuestion());
                    QuestionManager.removeQuestionReponse(question,reponse,Questions_Joueur1.getText().toString());
                }
                Questions_Joueur2.setText(Questions_Joueur1.getText());
            }
        });
    }
}