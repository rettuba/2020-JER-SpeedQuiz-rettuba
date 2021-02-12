package com.rettuba.speedquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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

    private int compteurJoueur1 = 0;
    private int compteurJoueur2 = 0;

    private Boolean buttonDoesNothing;

    Runnable questionRunnable;
    Handler handler;
    QuestionManager questionManager;
    Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*Boutons centraux*/
        BT_Menu = findViewById(R.id.bt_menu);
        BT_Rejouer = findViewById(R.id.bt_rejouer);
        /*Boutons de jeux*/
        BT_Joueur1 = findViewById(R.id.bt_joueur1);
        BT_Joueur2 = findViewById(R.id.bt_joueur2);
        /*Compteurs*/
        Compteur_Joueur1 = findViewById(R.id.compteur_joueur1);
        Compteur_Joueur2 = findViewById(R.id.compteur_joueur2);
        /*Noms*/
        Nom_Joueur1 = findViewById(R.id.nom_joueur1);
        Nom_Joueur2 = findViewById(R.id.nom_joueur2);
        /*Questions*/
        Questions_Joueur1 = findViewById(R.id.questions_joueur1);
        Questions_Joueur2 = findViewById(R.id.questions_joueur2);

        /*Rendre les deux boutons "Menu" et "Rejouer" invisible*/
        BT_Menu.setVisibility(View.GONE);
        BT_Rejouer.setVisibility(View.GONE);

        /*Récupérer le nom des joueurs*/
        Intent resultActivity = getIntent();
        String nom_joueur1 = resultActivity.getStringExtra("nom_joueur1");
        String nom_joueur2 = resultActivity.getStringExtra("nom_joueur2");

        /*Afficher le nom des joueurs*/
        Nom_Joueur1.setText(nom_joueur1);
        Nom_Joueur2.setText(nom_joueur2);

        questionManager = new QuestionManager();

    }

    @Override
    protected void onResume() {
        super.onResume();

        BT_Joueur1.setOnClickListener((v) -> {
            if (!buttonDoesNothing) {
                Questions_Joueur2.setText("Bloqué");
                BT_Joueur2.setEnabled(false);
                Compteur_Joueur1.setText("");
                if (question.getReponse()) {
                    compteurJoueur1 += 1;
                    buttonDoesNothing = true;
                } else {
                    if (compteurJoueur1 > 0) {
                        compteurJoueur1 -= 1;
                        buttonDoesNothing = true;
                    }
                }
                Compteur_Joueur1.setText("" + compteurJoueur1);
            }
        });
        BT_Joueur2.setOnClickListener((v) -> {
            if (!buttonDoesNothing) {
                Questions_Joueur1.setText("Bloqué");
                BT_Joueur1.setEnabled(false);
                Compteur_Joueur2.setText("");
                if (question.getReponse()) {
                    compteurJoueur2 += 1;
                    buttonDoesNothing = true;
                } else {
                    if (compteurJoueur2 > 0) {
                        compteurJoueur2 -= 1;
                        buttonDoesNothing = true;
                    }
                }
                Compteur_Joueur2.setText("" + compteurJoueur2);
            }
        });
        BT_Rejouer.setOnClickListener((v) -> {
            restartNewGame(questionRunnable);
        });
        BT_Menu.setOnClickListener((v) -> {
            Intent startActivity = new Intent(MainActivity.this, menu_speed_quiz.class);
            startActivity(startActivity);
            finish();
        });

        startNewGame();
    }

    /**
     * Commencer une partie
     */
    private void startNewGame() {
        startCountDownTimer();
    }

    /**
     * Gerer l'affichage des questions et du score final
     */
    private void startQuestionIterative() {
        buttonDoesNothing = false;
        handler = new Handler();
        questionRunnable = new Runnable() {
            @Override
            public void run() {
                if (questionManager.isLastQuestion(questionManager.getQuestionList())) {
                    buttonDoesNothing = true;
                    displayMenuButton();
                    resultatFinal();
                    unlockPlayer();
                    handler.removeCallbacks(this);
                } else {
                    buttonDoesNothing = false;
                    unlockPlayer();
                    startNewQuestionList();
                    handler.postDelayed(this, 2000);
                }
            }
        };
        handler.postDelayed(questionRunnable, 1000);
    }

    /**
     * Afficher une nouvelle question
     */
    public void startNewQuestionList() {
        question = questionManager.questionAleatoire();

        Questions_Joueur1.setText(question.getQuestion());
        Questions_Joueur2.setText(question.getQuestion());
    }

    /**
     * Afficher le resultat selon le score des joueurs
     */
    public void resultatFinal() {

        if (compteurJoueur1 > compteurJoueur2) {
            Questions_Joueur1.setText("Gagné");
            Questions_Joueur2.setText("Perdu");
        } else if (compteurJoueur1 < compteurJoueur2) {
            Questions_Joueur1.setText("Perdu");
            Questions_Joueur2.setText("Gagné");
        } else {
            Questions_Joueur1.setText("Égalité");
            Questions_Joueur2.setText("Égalité");
        }

    }

    /**
     * Commencer le decompte
     */
    private void startCountDownTimer() {
        new CountDownTimer(6000, 1000) {

            public void onTick(long millisUntilFinished) {

                buttonDoesNothing = true;

                Questions_Joueur1.setText("" + millisUntilFinished / 1000);
                Questions_Joueur2.setText("" + millisUntilFinished / 1000);
            }

            public void onFinish() {
                Questions_Joueur1.setText("GO");
                Questions_Joueur2.setText("GO");
                startQuestionIterative();
            }
        }.start();
    }

    /**
     * Commencer une nouvelle partie
     *
     * @param questionRunnable
     */
    private void restartNewGame(Runnable questionRunnable) {
        questionManager = new QuestionManager();
        hideMenuButton();
        resetScore();
        startNewGame();
    }

    /**
     * Rendre les boutons Menu et Rejouer visible
     */
    public void displayMenuButton() {
        BT_Rejouer.setVisibility(View.VISIBLE);
        BT_Menu.setVisibility(View.VISIBLE);
    }

    /**
     * Rendre les boutons Menu et Rejouer invisible
     */
    public void hideMenuButton() {
        BT_Rejouer.setVisibility(View.GONE);
        BT_Menu.setVisibility(View.GONE);
    }

    /**
     * Reinitialiser les compteurs
     */
    public void resetScore() {
        Compteur_Joueur1.setText("0");
        Compteur_Joueur2.setText("0");
        compteurJoueur1 = 0;
        compteurJoueur2 = 0;
    }

    /**
     * Debloquer les boutons des joueurs
     */
    public void unlockPlayer() {
        BT_Joueur1.setEnabled(true);
        BT_Joueur2.setEnabled(true);
    }


}