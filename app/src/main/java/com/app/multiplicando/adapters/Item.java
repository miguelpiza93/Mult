package com.app.multiplicando.adapters;


/**
 * Created by Miguel on 24/02/2016.
 */
public class Item {

    private String nombre;
    private int numero;

    public Item(){

    }

    public Item(String nombre, int numero) {
        this.nombre = nombre;
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}