/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cliente;

import formulario.cliente.PedidoHistorialItem;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Box;
import javax.swing.JPanel;
import modelos.Pedido;

/**
 *
 * @author Alex
 */
public class ControladorPanelHistorialPedidos {

    public static JPanel panel;
    private static List<PedidoHistorialItem> lista = new ArrayList<>();
    private static int contador = 0;

    public ControladorPanelHistorialPedidos() {
    }

    public ControladorPanelHistorialPedidos(JPanel panel) {
        ControladorPanelHistorialPedidos.panel = panel;
    }

    public void agregarNuevo(Pedido pedido) {
        contador++;
        lista.add(new PedidoHistorialItem(pedido, "Pedido " + contador));
        panel.removeAll();
        for (int i = lista.size()-1; i >=0; i--) {
            panel.add(lista.get(i));
            panel.add(Box.createRigidArea(new Dimension(5, 10)));
        }
        panel.revalidate();
    }

}
