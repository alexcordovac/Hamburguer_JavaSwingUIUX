/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cliente;

import formulario.cliente.PedidoItem;
import java.util.HashMap;
import javax.swing.JPanel;
import modelos.Producto;

/**
 *
 * @author Alex
 */
public class ControladorPanelPedidos {
    
    public static JPanel panelPedidos;
    public static HashMap<Producto, PedidoItem> hashPedidosItem = new HashMap<>();
    
    public ControladorPanelPedidos() {
    }

    public ControladorPanelPedidos(JPanel panelPedidos) {
        this.panelPedidos = panelPedidos;
    }
    
    public void pintarListaPedidos(){
        if(hashPedidosItem.isEmpty()) return;
        
        hashPedidosItem.forEach((k, v) -> {
            panelPedidos.add(v);
        });
        
        panelPedidos.revalidate();
        panelPedidos.repaint();
    }
    
    public void añadirNuevoItem(Producto pedido){
        if(hashPedidosItem.containsKey(pedido)) return;
        
        PedidoItem pedidoItem = new PedidoItem(pedido);
        hashPedidosItem.put(pedido, pedidoItem);
        panelPedidos.add(pedidoItem);
        panelPedidos.revalidate();
    }
    
    public void eliminarItem(Producto producto){
        if(!hashPedidosItem.containsKey(producto)) return;
        
        PedidoItem pedidoItem = hashPedidosItem.get(producto);
        panelPedidos.remove(pedidoItem);
        hashPedidosItem.remove(producto);
        panelPedidos.revalidate();
        panelPedidos.repaint();
    }
    
    public void cambiarCantidad(Producto producto){
        if(!hashPedidosItem.containsKey(producto)) return;
        
        PedidoItem pedidoItem = hashPedidosItem.get(producto);
        pedidoItem.getLblCantidad().setText("x"+String.valueOf(producto.getCantidad()));
        hashPedidosItem.put(producto, pedidoItem);
    }
}
