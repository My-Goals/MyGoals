package com.mygoals;

public class Seleccion {
    private String nombre;
    private String cantidad;

    public Seleccion(String nombre, String cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Seleccion{" +
                "nombre='" + nombre + '\'' +
                ", cantidad='" + cantidad + '\'' +
                '}';
    }
}




