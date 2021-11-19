/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;

/**
 *
 * @author Alex
 */
public class Producto implements Serializable{
    
    private String nombre;
    private int cantidad;

    public Producto() {
    }

    public Producto(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
    
    public void incremantarCantidad(){
        this.cantidad++;    
    }
    
    public void decrementarCantidad(){
        if(this.cantidad == 0) return;
        this.cantidad--;
    }
    
    /*GETTER Y SETTERS*/

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "{"+nombre + " x" + cantidad +"}";
    }
    
    
    
}
