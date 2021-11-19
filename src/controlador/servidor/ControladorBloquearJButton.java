/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.servidor;

import javax.swing.JButton;

/**
 *
 * @author Alex
 */
public class ControladorBloquearJButton {

    public static JButton boton = new JButton();

    public ControladorBloquearJButton() {
    }

    public ControladorBloquearJButton(JButton boton) {
        ControladorBloquearJButton.boton = boton;
    }

    public void deshabilitar() {
        new Thread(() -> {
            synchronized (boton) {
                try {
                    boton.setEnabled(false);
                    boton.wait();
                } catch (InterruptedException e) {
                    // Happens if someone interrupts your thread.
                }
            }

        }).start();
    }

    public void habilitar() {
        new Thread(() -> {
            synchronized (boton) {
                boton.setEnabled(true);
                boton.notify();
            }
        }).start();
    }

}
