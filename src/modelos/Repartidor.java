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
public class Repartidor implements Serializable{
    
    private String nombre;

    public Repartidor() {
    }

    public Repartidor(String nombre) {
        this.nombre = nombre;
    }

    /*GETTER Y SETTER*/
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Repartidor{" + "nombre=" + nombre + '}';
    }
    
    
    
}
