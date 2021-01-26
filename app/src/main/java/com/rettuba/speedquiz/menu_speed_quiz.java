package com.rettuba.speedquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

public class menu_speed_quiz extends AppCompatActivity {


    /**Bouton nouveau joueur**/
    private MaterialButton bouton_nouveau_joueur;
    /**Bouton jouer**/
    private MaterialButton bouton_jouer;
    /**Booleens**/
    boolean rempli_joueur1 = false;
    boolean rempli_joueur2 = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_speed_quiz);


        /**Bouton jouer**/
        bouton_jouer = findViewById(R.id.bt_jouer);
        bouton_jouer.setEnabled(true);
        bouton_jouer.setVisibility(View.VISIBLE);

        /**Bouton nouveau joueur**/
        bouton_nouveau_joueur = findViewById(R.id.bt_nouveau_joueur);

        bouton_nouveau_joueur.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                EditText ET_joueur1 = new EditText(menu_speed_quiz.this);
                ET_joueur1.setHint("Nom joueur 1");
                ET_joueur1.setTextSize(20);
                ET_joueur1.setWidth(300);
                ET_joueur1.setHeight(60);

            }
        });

      /*nom_joueur1.addTextChangedListener(new TextWatcher() {
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
        });*/

      /*nom_joueur2.addTextChangedListener(new TextWatcher() {
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
        });*/

     bouton_jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent resultatActivity = new Intent(menu_speed_quiz.this,MainActivity.class);
                startActivity(resultatActivity);
            }
        });
    }
}










