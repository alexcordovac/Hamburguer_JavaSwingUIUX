/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cliente;

import formulario.cliente.FormOrdenar;
import formulario.cliente.PackItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 *
 * @author Alex
 */
public class ControladorFormOrdenar {

    private FormOrdenar vista;
    private ControladorListaPedidos ctrlListaPedidos = new ControladorListaPedidos();

    public ControladorFormOrdenar(FormOrdenar vista) {
        this.vista = vista;
        pintarPacks();
    }

    public void pintarPacks() {

        JPanel panel = vista.getPanelProductos();
        for (int i = 1; i < 5; i++) {
            PackItem paquete = new PackItem("Pack " + String.valueOf(i));

            /*Función del botón agregar*/
            ActionListener btnAgregar = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ctrlListaPedidos.agregar(paquete.getProducto());
                }
            };

            /*Función del botón eliminar*/
            ActionListener btnEliminar = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ctrlListaPedidos.eliminar(paquete.getProducto());
                }
            };
            
            paquete.getBtnAgregar().addActionListener(btnAgregar);
            paquete.getBtnEliminar().addActionListener(btnEliminar);

            paquete.getLblPack().setText("Pack " + (i));
            panel.add(paquete);
        }
    }

    private void iniComponentes() {

    }

}
