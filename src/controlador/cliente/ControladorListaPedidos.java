/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cliente;

import controlador.cliente.ControladorPanelPedidos;
import modelos.Pedido;
import modelos.Producto;

/**
 *
 * @author Alex
 */
public class ControladorListaPedidos {
    
    public static CarritoPedido carrito = new CarritoPedido();
    private ControladorPanelPedidos ctrlPanelPedidos = new ControladorPanelPedidos();
    
    public void agregar(Producto producto){
        if((carrito.añadir(producto) == 1)){
            ctrlPanelPedidos.añadirNuevoItem(producto);
        }else{
            ctrlPanelPedidos.cambiarCantidad(producto);
        }
    }
    
    public void eliminar(Producto producto){
        int res = carrito.eliminar(producto);
        if(res == -1){
            ctrlPanelPedidos.eliminarItem(producto);
        }else if(res == 1){
            //El decremento lo hace el CarritoPedido(carrito)
            ctrlPanelPedidos.cambiarCantidad(producto);
        }
    }
    
}
