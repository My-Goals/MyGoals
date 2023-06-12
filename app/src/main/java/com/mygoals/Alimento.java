package com.mygoals;

public class Alimento {
    private String nombre;
    private int grasas;
    private int carbohidratos;
    private int proteinas;
    private int kcal;

    public Alimento(String nombre, int grasas, int carbohidratos, int proteinas, int kcal) {
        this.nombre = nombre;
        this.grasas = grasas;
        this.carbohidratos = carbohidratos;
        this.proteinas = proteinas;
        this.kcal = kcal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getGrasas() {
        return grasas;
    }

    public void setGrasas(int grasas) {
        this.grasas = grasas;
    }

    public int getCarbohidratos() {
        return carbohidratos;
    }

    public void setCarbohidratos(int carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    public int getProteinas() {
        return proteinas;
    }

    public void setProteinas(int proteinas) {
        this.proteinas = proteinas;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    @Override
    public String toString() {
        return "Alimento{" +
                "nombre='" + nombre + '\'' +
                ", grasas=" + grasas +
                ", carbohidratos=" + carbohidratos +
                ", proteinas=" + proteinas +
                ", kcal=" + kcal +
                '}';
    }
}
