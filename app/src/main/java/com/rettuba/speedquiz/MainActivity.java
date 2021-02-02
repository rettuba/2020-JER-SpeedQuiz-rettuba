package com.rettuba.speedquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
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

    private int compteur;

    Runnable questionRunnable = null;
    Handler handler;
    QuestionManager questionManager;
    Question question;


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

        startNewGame();


        QuestionManager maQuestion = new QuestionManager();

        ArrayList question = Source.listeDeQuestions();
        ArrayList reponse = Source.listeDesReponses();

        BT_Joueur1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (question.isEmpty()) {
                    Questions_Joueur1.setText("Fini ! Il est bien mon jeu hein :)");
                } else {
                    Questions_Joueur1.setText(maQuestion.getQuestion());
                    QuestionManager.removeQuestionReponse(question, reponse, Questions_Joueur1.getText().toString());
                    compteurPoint(maQuestion.getReponse());
                    Compteur_Joueur1.setText("test");
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
                    QuestionManager.removeQuestionReponse(question, reponse, Questions_Joueur1.getText().toString());
                    Compteur_Joueur1.setText("test");
                }
                Questions_Joueur2.setText(Questions_Joueur1.getText());
            }
        });
    }
    private void startNewGame() { startCountDownTimer();}

    private void startQuestionIterative() {
        handler = new Handler();
        questionRunnable = new Runnable() {
            @Override
            public void run() {
                if(questionManager.isLastQuestion(Source.listeDeQuestions())) {
                    resultatFinal(Integer.parseInt(Questions_Joueur1.getText().toString()), Integer.parseInt(Questions_Joueur2.getText().toString()));
                    handler.removeCallbacks(this);
                    displayMenuButton();
                }else{
                    unlockPlayer();
                    startNewQuestionList();
                    handler.postDelayed(this, 2000);
                }
            }
        };
        handler.postDelayed(questionRunnable, 1000);
    }

    public void startNewQuestionList(){
        Questions_Joueur1.setText("TEST");
        Questions_Joueur2.setText("TEST");
    }

    public void resultatFinal(int ptsJoueur1, int ptsJoueur2){

        if(ptsJoueur1 > ptsJoueur2){
            Questions_Joueur1.setText("Gagné");
            Questions_Joueur2.setText("Perdu");
        }
        else if(ptsJoueur1 > ptsJoueur2){
            Questions_Joueur1.setText("Perdu");
            Questions_Joueur2.setText("Gagné");
        }else{
            Questions_Joueur1.setText("Egalité");
            Questions_Joueur2.setText("Egalité");
        }

    }

    private void startCountDownTimer(){
        new CountDownTimer(6000,1000){

            public void onTick(long millisUntilFinished) {
                Questions_Joueur1.setText(""+millisUntilFinished / 1000);
                Questions_Joueur2.setText(""+millisUntilFinished / 1000);
            }

            public void onFinish(){
                Questions_Joueur1.setText("GO");
                Questions_Joueur2.setText("GO");
                startQuestionIterative();
            }
        }.start();
    }

    private void restartNewGame(Runnable questionRunnable){
        questionManager = new QuestionManager();
        hideMenuButton();
        resetScore();
        startNewGame();
    }
    public void displayMenuButton(){
        BT_Joueur1.setVisibility(View.VISIBLE);
        BT_Joueur2.setVisibility(View.VISIBLE);
    }
    public void hideMenuButton(){
        BT_Rejouer.setVisibility(View.GONE);
        BT_Menu.setVisibility(View.GONE);
    }
    public void resetScore(){
        Compteur_Joueur1.setText("0");
        Compteur_Joueur2.setText("0");
    }

    public void unlockPlayer(){
        BT_Joueur1.setEnabled(true);
        BT_Joueur2.setEnabled(true);
    }

    private int compteurPoint(int reponse){
        if (reponse == 1){
            compteur ++;
        }
        return compteur;
    }
}
