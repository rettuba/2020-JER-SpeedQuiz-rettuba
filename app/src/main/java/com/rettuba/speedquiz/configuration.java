package com.rettuba.speedquiz;

import android.content.res.Resources;
import android.os.Bundle;

/**
 * Classe permettant de stocker de manière globale toutes les informations concernant la configuration
 */
public class configuration {

    public static int valeurSeekBar = 2;
    public static boolean valeurSwitch = false;

    /**
     * Modifie la valeur actuelle de la SeekBar par une nouvelle valeur
     * @param nouvelleValeurSeekBar
     */
    public static void setValeurSeekBar(int nouvelleValeurSeekBar){
        valeurSeekBar = nouvelleValeurSeekBar;
    }

    public static void setValeurSwitch(boolean nouvelleValeurSwitch){
        valeurSwitch = nouvelleValeurSwitch;
    }
    /**
     * @return la valeur de la SeekBar
     */
    public static int getValeurSeekBar(){
        return valeurSeekBar;
    }

    /**
     * @return la valeur du Switch
     */
    public static boolean getValeurSwitch(){
        return valeurSwitch;
    }

}
