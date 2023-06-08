package com.mygoals;

public class Notas {
    private String date;
    private String nota;

    public Notas(){

    }

    public Notas(String date, String nota) {
        this.date = date;
        this.nota = nota;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
