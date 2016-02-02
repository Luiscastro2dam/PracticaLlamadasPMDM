package com.example.clase.practicallamadas.baseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Clase on 14/11/2015.
 */
public class Ayudante extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "llamadas.sqlite";
    public static final int DATABASE_VERSION = 1;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null,
                DATABASE_VERSION);
    }
    //se encargara de crear las tablas si no existen
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        sql="create table "+Agenda.TablaLlamadas.TABLA+
                " ("+ Agenda.TablaLlamadas._ID+
                " integer primary key autoincrement, "+
                Agenda.TablaLlamadas.LLAMADA+" text, "+
                Agenda.TablaLlamadas.DIA+" text)";
        db.execSQL(sql);
    }
    // las actualizara
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="drop table if exists "+ Agenda.TablaLlamadas.TABLA;
        db.execSQL(sql);
        onCreate(db);
    }
}
