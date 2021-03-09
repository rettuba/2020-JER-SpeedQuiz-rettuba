package com.rettuba.speedquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class main_configuration extends AppCompatActivity {

    SeekBar main_seekBar;
    TextView vitesse_question;
    Button bt_valider;
    Button bt_quitter;
    Switch sw_theme;

    private String phraseVitesseQuestion;
    private int delaisDeBase;

    public main_configuration() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Modifie le thème selon l'option choisie en configuration
        if (configuration.getValeurSwitch()){
            setTheme(R.style.Theme_SpeedQuiz_dark);
        }else{
            setTheme(R.style.Theme_SpeedQuiz_light);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_configuration);

        main_seekBar = findViewById(R.id.main_seekbar);
        vitesse_question = findViewById(R.id.ET_vitesse_questions);
        bt_valider = findViewById(R.id.bt_valider);
        sw_theme = findViewById(R.id.sw_theme);
        bt_quitter = findViewById(R.id.quitterSansEnregister);

        // Recupere la phrase qui affiche le nombre de seconde entre les questions
        phraseVitesseQuestion = vitesse_question.getText().toString();

        setProgressOfSeekBar(); // Modifie la valeur de la SeekBar
        setValueOfSwitch();     // Modifie la valeur du Switch

        // Stocker la valeur de la SeekBar dans une variable
        delaisDeBase = main_seekBar.getProgress();
        // Afficher la phrase avec les secondes
        affichageDelaisEntreQuesions();

        /**
         * A chaque changement de valeur dans la SeekBar, modifier la phrase qui affiche les secondes
         */
        main_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int delais = progress;
                affichageDelaisEntreQuesions();
                setDelais(delais);
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        /**
         * Sur le clic du bouton, retourner au menu principal en modifiant les valeurs de la SeekBar et du Switch dans la classe Configuration
         */
        bt_valider.setOnClickListener((v) -> {
            Intent menu_speed_quiz = new Intent(main_configuration.this, menu_speed_quiz.class);
            startActivity(menu_speed_quiz);
            configuration.setValeurSeekBar(delaisDeBase);
            configuration.setValeurSwitch(sw_theme.isChecked());
        });

        /**
         * Sur le clic du bouton, retourner au menu principal
         */
        bt_quitter.setOnClickListener((v) -> {
            Intent menu_speed_quiz = new Intent(main_configuration.this, menu_speed_quiz.class);
            startActivity(menu_speed_quiz);
        });


    }

    /**
     * Affiche le nombre de secondes par rapport à l'avancé de la Seekbar
     */
    public void affichageDelaisEntreQuesions() {
        String plurielSeconde = "secondes";
        if (main_seekBar.getProgress() <= 1) {
            plurielSeconde = "seconde";
        } else {
            plurielSeconde = "secondes";
        }
        vitesse_question.setText(phraseVitesseQuestion + main_seekBar.getProgress() + " " + plurielSeconde);
    }

    /**
     * Modifie le delais de base
     * @param delais
     */
    public void setDelais(int delais){
        delaisDeBase = delais;
    }

    /**
     * Modifie la valeur de la SeekBar
     */
    public void setProgressOfSeekBar(){
            main_seekBar.setProgress(configuration.getValeurSeekBar());
    }

    /**
     * Modifie la valeur du Switch
     */
    public void setValueOfSwitch(){
        sw_theme.setChecked(configuration.getValeurSwitch());
    }
}