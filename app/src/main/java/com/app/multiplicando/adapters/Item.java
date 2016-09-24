package com.app.multiplicando.adapters;


/**
 * Created by Miguel on 24/02/2016.
 */
public class Item {

    private String nombre;
    private int numero;
    private String descripcion;

    public Item(){

    }

    public Item(String nombre, String descripcion, int numero) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}