package com.mygoals;

public class Alimento {

    private double carbohidratos;
    private double energia;
    private double grasas;
    private String nombre;
    private double proteinas;

    public Alimento(double carbohidratos, double energia, double grasas, String nombre, double proteinas) {
        this.carbohidratos = carbohidratos;
        this.energia = energia;
        this.grasas = grasas;
        this.nombre = nombre;
        this.proteinas = proteinas;
    }

    public double getCarbohidratos() {
        return carbohidratos;
    }

    public void setCarbohidratos(double carbohidratos) {
        this.carbohidratos = carbohidratos;
    }

    public double getEnergia() {
        return energia;
    }

    public void setEnergia(double energia) {
        this.energia = energia;
    }

    public double getGrasas() {
        return grasas;
    }

    public void setGrasas(double grasas) {
        this.grasas = grasas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getProteinas() {
        return proteinas;
    }

    public void setProteinas(double proteinas) {
        this.proteinas = proteinas;
    }
}

