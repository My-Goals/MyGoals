package com.mygoals;

public class Medicion {
    private String fecha;
    private String gc;
    private String idUsu;
    private String imc;

    public Medicion(String fecha, String gc, String idUsu, String imc) {
        this.fecha = fecha;
        this.gc = gc;
        this.idUsu = idUsu;
        this.imc = imc;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getGc() {
        return gc;
    }

    public void setGc(String gc) {
        this.gc = gc;
    }

    public String getIdUsu() {
        return idUsu;
    }

    public void setIdUsu(String idUsu) {
        this.idUsu = idUsu;
    }

    public String getImc() {
        return imc;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }
}
