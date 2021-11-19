/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import controlador.cliente.CarritoPedido;
import java.io.Serializable;

/**
 *
 * @author Alex
 */
public class Pedido implements Serializable {

    private Cliente cliente;
    private Repartidor repartidor;
    private String estado;
    private String tiempoEntrega;
    private CarritoPedido carrito;

    public Pedido() {
    }

    public Pedido(Cliente cliente, CarritoPedido pedido) {
        this.cliente = cliente;
        this.carrito = pedido;
    }

    /*GETTERS Y SETTERS*/
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public CarritoPedido getCarrito() {
        return carrito;
    }

    public void setCarrito(CarritoPedido carrito) {
        this.carrito = carrito;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }

    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public String getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(String tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pedido{" + "cliente=" + cliente + ", repartidor=" + repartidor + ", estado=" + estado + ", tiempoEntrega=" + tiempoEntrega + ", carrito=" + carrito + '}';
    }
    
    
}
