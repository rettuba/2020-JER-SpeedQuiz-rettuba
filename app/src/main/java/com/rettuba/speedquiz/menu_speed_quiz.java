package com.rettuba.speedquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.button.MaterialButton;

public class menu_speed_quiz extends AppCompatActivity {


    /**Bouton nouveau joueur**/
    private MaterialButton bouton_nouveau_joueur;
    /**Bouton jouer**/
    private MaterialButton bouton_jouer;
    /**Layout**/
    private LinearLayout linearLayout_menu;
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

        /**Layout**/
        linearLayout_menu = findViewById(R.id.linearLayout_menu);

        EditText ET_joueur1 = new EditText(this);
        ET_joueur1.setHint("Nom joueur 1");
        ET_joueur1.setTextSize(20);
        ET_joueur1.setHeight(100);

        EditText ET_joueur2 = new EditText(this);
        ET_joueur2.setHint("Nom joueur 2");
        ET_joueur2.setTextSize(20);
        ET_joueur2.setHeight(100);


        bouton_nouveau_joueur.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                linearLayout_menu.addView(ET_joueur1);
                linearLayout_menu.addView(ET_joueur2);
                ET_joueur2.setVisibility(View.GONE);


            }
        });

        ET_joueur1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ET_joueur2.setVisibility(View.VISIBLE);

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

      ET_joueur1.addTextChangedListener(new TextWatcher() {
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

      ET_joueur2.addTextChangedListener(new TextWatcher() {
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
                startActivity(resultatActivity);
            }
        });
    }
}










