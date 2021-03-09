package com.rettuba.speedquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SpeedQuizSQLiteOpenHelper extends SQLiteOpenHelper  {

    static String DB_NAME="SpeedQuiz.db";
    static int DB_VERSION=1;
    public SpeedQuizSQLiteOpenHelper(Context context)
    {
        super(context,DB_NAME,null,DB_VERSION);
    }

    /**
     * Source des questions
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateDatatableQuiz = "CREATE TABLE quiz(idQuiz INTEGER PRIMARY KEY,question TEXT,reponse INTEGER);";
        db.execSQL(sqlCreateDatatableQuiz);
        db.execSQL("INSERT INTO quiz VALUES(1,\"La racine de 145 est un nombre entier\",0)");
        db.execSQL("INSERT INTO quiz VALUES(2,\"La suisse a 23 cantons\",0)");
        db.execSQL("INSERT INTO quiz VALUES(3,\"La suisse a plus de 8 millions d'habitants (année : 2020)\",1)");
        db.execSQL("INSERT INTO quiz VALUES(4,\"La Russie est le plus grand pays au monde\",1)");
        db.execSQL("INSERT INTO quiz VALUES(5,\"L'Afrique est un pays\",0)");
        db.execSQL("INSERT INTO quiz VALUES(6,\"La Lune est un satellite naturel\",1)");
        db.execSQL("INSERT INTO quiz VALUES(7,\"La terre a moins d'un milliard d'années\",0)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

}

