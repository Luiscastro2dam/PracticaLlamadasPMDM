package com.example.clase.practicallamadas.baseDatos;

/**
 * Created by Clase on 29/01/2016.
 */
public class TablaLlamadas {
    int llamada, dia, idllamada;

    public TablaLlamadas() {
    }

    public TablaLlamadas(int llamada, int dia, int idllamada) {
        this.llamada = llamada;
        this.dia = dia;

        this.idllamada = idllamada;
    }

    public int getLlamada() {
        return llamada;
    }

    public void setLlamada(int llamada) {
        this.llamada = llamada;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }



    public int getIdllamada() {
        return idllamada;
    }

    public void setIdllamada(int idllamada) {
        this.idllamada = idllamada;
    }

    @Override
    public String toString() {
        return "TablaLlamadas{" +
                "llamada=" + llamada +
                ", dia=" + dia +
                ", idllamada=" + idllamada +
                '}';
    }
}
