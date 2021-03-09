package com.rettuba.speedquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class About extends AppCompatActivity {

    Button bt_quitter; // Bouton qui permettra de revenir au menu principal


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Modifie le thème selon l'option choisie en configuration
        if (configuration.getValeurSwitch()){
            setTheme(R.style.Theme_SpeedQuiz_dark);
        }else{
            setTheme(R.style.Theme_SpeedQuiz_light);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        bt_quitter = findViewById(R.id.bt_quitter);


        /**
         * Sur le clic du bouton, revenir au menu principal
         */
        bt_quitter.setOnClickListener((v) -> {
            Intent menu_speed_quiz = new Intent(About.this, menu_speed_quiz.class);
            startActivity(menu_speed_quiz);
        });
    }
}