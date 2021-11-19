/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.servidor;

import formulario.servidor.FormInicio;
import formulario.servidor.ItemSolicitudPedido;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JPanel;
import modelos.Pedido;

/**
 *
 * @author Alex
 */
public class ControladorPanelPedidosEntrantes {
    
    private static JPanel panelPedidosEntrantes;
    private static HashMap<Pedido, ItemSolicitudPedido> listaPedidosItem; 
    
    public ControladorPanelPedidosEntrantes() {
    }
    
    public ControladorPanelPedidosEntrantes(JPanel panelPedidosEntrantes) {
        ControladorPanelPedidosEntrantes.panelPedidosEntrantes = panelPedidosEntrantes;
        listaPedidosItem = new HashMap<>();
    }
    
    public void pintarNuevoItem(Pedido pedido){
        if(!listaPedidosItem.containsKey(pedido))
            listaPedidosItem.put(pedido, new ItemSolicitudPedido(pedido));
        panelPedidosEntrantes.add(listaPedidosItem.get(pedido));
        panelPedidosEntrantes.revalidate();
    }
    
    public void eliminarItem(Pedido pedido){
        if(!listaPedidosItem.containsKey(pedido)) return;
        panelPedidosEntrantes.remove(listaPedidosItem.get(pedido));
        panelPedidosEntrantes.revalidate();
        panelPedidosEntrantes.repaint();
        listaPedidosItem.remove(pedido);
    }
    
}
