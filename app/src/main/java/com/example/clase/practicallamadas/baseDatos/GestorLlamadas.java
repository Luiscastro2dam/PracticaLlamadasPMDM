package com.example.clase.practicallamadas.baseDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 2dam on 21/10/2015.
 */
public class GestorLlamadas {
    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorLlamadas(Context c) {
        abd = new Ayudante(c);
    }

    public void open() {
        bd = abd.getWritableDatabase();
    }

    public void openRead() {
        bd = abd.getReadableDatabase();
    }

    public void close() {
        abd.close();
    }

    //Si el insert falla devuelve -1
    public long insert(TablaLlamadas llamada) {
        ContentValues valores = new ContentValues();
        valores.put(Agenda.TablaLlamadas.LLAMADA,
                llamada.getLlamada());
        valores.put(Agenda.TablaLlamadas.DIA,
                llamada.getDia());
        long id = bd.insert(Agenda.TablaLlamadas.TABLA,
                null, valores);
        return id;
    }

    public List<TablaLlamadas> select() {
        List<TablaLlamadas> lista;
        lista = new ArrayList<TablaLlamadas>();
        Cursor cursor = bd.query(Agenda.TablaLlamadas.TABLA, null,
                null, null, null, null, null, null);
        cursor.moveToFirst();
        TablaLlamadas llamadas;
        while (!cursor.isAfterLast()) {
            llamadas = getRow(cursor);
            lista.add(llamadas);
            cursor.moveToNext();
        }
        cursor.close();
        if (lista == null) {
            lista.add(new TablaLlamadas());
            return lista;
        }
        return lista;
    }

    public TablaLlamadas getRow(Cursor c) {
        TablaLlamadas p = new TablaLlamadas();
        p.setIdllamada(c.getInt(c.getColumnIndex(Agenda.TablaLlamadas._ID)));
        p.setLlamada(c.getInt(c.getColumnIndex(Agenda.TablaLlamadas.LLAMADA)));
        p.setDia(c.getInt(c.getColumnIndex(Agenda.TablaLlamadas.DIA)));
        return p;
    }

    public int devorverdatos(int i, int dia) {
        //SELECT  COUNT(*) FROM  llamadas  where llamada=0 and dia=1
        String lla=String.valueOf(i);
         String di = String.valueOf(dia);
         String[] args = new String[] {lla,di};
        System.out.println("luisss");
         Cursor c = bd.rawQuery(" SELECT count(*) FROM llamadas WHERE llamada=? and dia=?", args);
         c.moveToFirst();
        System.out.println("luis22" + c.getInt(0));
         return c.getInt(0);

    }
    public void datosInicio(){
        ContentValues valores = new ContentValues();
        valores.put(Agenda.TablaLlamadas.LLAMADA,
                0);
        valores.put(Agenda.TablaLlamadas.DIA,
                1);
        long id = bd.insert(Agenda.TablaLlamadas.TABLA,
                null, valores);
    }
    public void delete(){
        bd.delete(Agenda.TablaLlamadas.TABLA,null,null);
    }

}
