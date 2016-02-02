package com.example.clase.practicallamadas.baseDatos;

import android.provider.BaseColumns;

public class Agenda {

    private Agenda(){
    }
    public static abstract class TablaLlamadas implements
            BaseColumns{
        public static final String TABLA = "llamadas";
        public static final String LLAMADA = "llamada";
        public static final String DIA  = "dia";



    }

}
