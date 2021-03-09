package com.rettuba.speedquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.android.material.button.MaterialButton;

public class menu_speed_quiz extends AppCompatActivity {

    // Bouton nouveau joueur
    private MaterialButton bouton_nouveau_joueur;
    // Layout
    private LinearLayout linearLayout_menu;
    // Booleens
    boolean rempli_joueur1 = false;
    boolean rempli_joueur2 = false;
    boolean canPress = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Modifie le thème selon l'option choisie en configuration
        if (configuration.getValeurSwitch()){
            setTheme(R.style.Theme_SpeedQuiz_dark);
        }else {
            setTheme(R.style.Theme_SpeedQuiz_light);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_speed_quiz);

        Toolbar mainToolBar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolBar);

        bouton_nouveau_joueur = findViewById(R.id.bt_nouveau_joueur);
        linearLayout_menu = findViewById(R.id.linearLayout_menu);

        /**
         * Créer le champ de saisie pour le joueur1
         */
        EditText ET_joueur1 = new EditText(this);
        ET_joueur1.setHint("Nom joueur 1");
        ET_joueur1.setTextSize(20);
        ET_joueur1.setHeight(200);
        ET_joueur1.setMaxLines(1);

        /**
         * Créer le champ de saisie pour le joueur1
         */
        EditText ET_joueur2 = new EditText(this);
        ET_joueur2.setHint("Nom joueur 2");
        ET_joueur2.setTextSize(20);
        ET_joueur2.setHeight(200);
        ET_joueur1.setMaxLines(1);

        /**
         * Créer le bouton pour jouer
         */
        MaterialButton bouton_jouer = new MaterialButton(this);
        bouton_jouer.setText("Jouer");
        bouton_jouer.setTextSize(25);
        bouton_jouer.setHeight(150);

        /**
         * Sur le clic du bouton nouveau joueur, ajouter les deux champs de saisie au Layout et rendre le premier champ visible
         */
        bouton_nouveau_joueur.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (canPress) {

                    linearLayout_menu.addView(ET_joueur1);
                    linearLayout_menu.addView(ET_joueur2);
                    ET_joueur2.setVisibility(View.GONE);
                    linearLayout_menu.addView(bouton_jouer);
                    bouton_jouer.setEnabled(false);
                    bouton_jouer.setVisibility(View.GONE);
                    ET_joueur1.requestFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.showSoftInput(ET_joueur1, InputMethodManager.SHOW_IMPLICIT);
                    canPress = false;
                }

            }
        });


        ET_joueur1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                ET_joueur2.setVisibility(View.VISIBLE); // Rendre le deuxième champ de saisie visible

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        /**
         * Vérifier que les deux champs de saisie soient ramplis, si oui rendre le clic sur le bouton jouer possible
         */
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
                        bouton_jouer.setVisibility(View.VISIBLE);
                    }
                }else{
                    rempli_joueur1 = false;
                    bouton_jouer.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        /**
         * Vérifier que les deux champs de saisie soient ramplis, si oui rendre le clic sur le bouton jouer possible
         */
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
                        bouton_jouer.setVisibility(View.VISIBLE);
                    }
                }else{
                    rempli_joueur2 = false;
                    bouton_jouer.setEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        /**
         * Sur le clic du bouton jouer, accéder au mainActivity et récupérer les noms des joueurs
         */
     bouton_jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent resultatActivity = new Intent(menu_speed_quiz.this,MainActivity.class);
                resultatActivity.putExtra("nom_joueur1",ET_joueur1.getText().toString());
                resultatActivity.putExtra("nom_joueur2",ET_joueur2.getText().toString());
                startActivity(resultatActivity);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * Selon le choix se rendre sur l'activité configuration ou about
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_configutation:
                Intent main_configuration = new Intent(menu_speed_quiz.this,main_configuration.class);
                startActivity(main_configuration); // Se rendre dans l'activité main_configuration
                return super.onOptionsItemSelected(item);
            case R.id.action_about:
                Intent about = new Intent(menu_speed_quiz.this,About.class);
                startActivity(about); // Se rendre dans l'activité About
                return super.onOptionsItemSelected(item);
        }

        return false;
    }

}