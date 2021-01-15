package com.rettuba.speedquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

public class menu_speed_quiz extends AppCompatActivity {

    /**Champs de saisie pour le nom des joueurs**/
    private EditText nom_joueur1;
    private EditText nom_joueur2;
    /**Bouton jouer**/
    private MaterialButton bouton_jouer;
    /**Booleens**/
    boolean rempli_joueur1 = false;
    boolean rempli_joueur2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_speed_quiz);



        /**Champs de saisie pour le nom des joueurs**/
        nom_joueur1 = findViewById(R.id.creer_joueur1);
        nom_joueur2 = findViewById(R.id.creer_joueur2);
        /**Bouton jouer**/
        bouton_jouer = findViewById(R.id.jouer);
        bouton_jouer.setEnabled(false);
        bouton_jouer.setVisibility(View.GONE);



        nom_joueur1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 1){
                    rempli_joueur1 = true;
                    if(rempli_joueur2) {
                        bouton_jouer.setEnabled(true);
                        bouton_jouer.setBackgroundColor(Color.BLUE);
                        bouton_jouer.setVisibility(View.VISIBLE);
                    }
                }else{
                    rempli_joueur1 = false;
                    bouton_jouer.setEnabled(false);
                    bouton_jouer.setBackgroundColor(Color.GRAY);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        nom_joueur2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 1){
                    rempli_joueur2 = true;
                    if(rempli_joueur1) {
                        bouton_jouer.setEnabled(true);
                        bouton_jouer.setBackgroundColor(Color.BLUE);
                        bouton_jouer.setVisibility(View.VISIBLE);
                    }
                }else{
                    rempli_joueur2 = false;
                    bouton_jouer.setEnabled(false);
                    bouton_jouer.setBackgroundColor(Color.GRAY);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        bouton_jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent resultatActivity = new Intent(menu_speed_quiz.this,MainActivity.class);
                resultatActivity.putExtra("nom_joueur1",nom_joueur1.getText().toString());
                resultatActivity.putExtra("nom_joueur2",nom_joueur2.getText().toString());
                startActivity(resultatActivity);
            }
        });
    }
}










