/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Alex
 */
public class ControladorGeneral {

    public ControladorGeneral() {
    }

    public static void mostrarOptionPane(String html, String titulo, int ancho, int tipo) {
        Runnable r = () -> {
            JOptionPane.showMessageDialog(null, String.format(html, ancho, ancho), titulo, tipo);
        };
        SwingUtilities.invokeLater(r);

    }

}
