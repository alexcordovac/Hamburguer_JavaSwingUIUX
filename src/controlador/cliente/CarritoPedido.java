/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import modelos.Producto;

/**
 *
 * @author Alex
 */
public class CarritoPedido implements Serializable{

    private List<Producto> carrito = new ArrayList<>();

    public CarritoPedido() {
    }

    /**
     * Función para añadir al carrito, si regresa 1, significa que tengo que
     * repintar la lista de pedidos
     */
    public int añadir(Producto producto) {
        if (carrito.contains(producto)) {
            int index = carrito.indexOf(producto);
            carrito.get(index).incremantarCantidad();
            return 0;
        } else {
            producto.incremantarCantidad();
            carrito.add(producto);
            return 1;
        }

    }

    /**
     * Función para eliminar del "carrito" de pedidos, regresa -1 para indicarme
     * que ese producto debe borrarse de la lista de pedidos
     */
    public int eliminar(Producto producto) {
        if (carrito.contains(producto)) {
            int index = carrito.indexOf(producto);
            if (carrito.get(index).getCantidad() <= 1) {
                carrito.remove(producto);
                return -1;
            }
            carrito.get(index).decrementarCantidad();
            return 1;
        }
        return 0;
    }

    /*GETTER Y SETTER*/
    public List<Producto> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<Producto> carrito) {
        this.carrito = carrito;
    }

    @Override
    public String toString() {
        return carrito+"";
    }
    
    
}
