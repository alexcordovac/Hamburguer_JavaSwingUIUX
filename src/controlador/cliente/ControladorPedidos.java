/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cliente;

import controlador.ControladorGeneral;
import formulario.cliente.ClienteSocket;
import formulario.cliente.FormPrincipalCliente;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import modelos.Cliente;
import modelos.Pedido;

/**
 *
 * @author Alex
 */
public class ControladorPedidos {

    public static FormPrincipalCliente vista;
    private boolean isProcesando = false;
    private ControladorPanelHistorialPedidos ctrlHistorial = new ControladorPanelHistorialPedidos();

    public ControladorPedidos() {
    }

    public ControladorPedidos(FormPrincipalCliente vista) {
        this.vista = vista;
        iniComponentes();
    }

    private void iniComponentes() {

        /*Función del botón realizar pedido*/
        vista.getBtnRegistrarPedido().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                realizarPedido();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        

    }

    private void realizarPedido() {
        if (vista.getLblNombreUsuario().getText().trim().equalsIgnoreCase("<html>Sin asignar</html>")
                || vista.getLblTelefono().getText().trim().equalsIgnoreCase("<html>Sin asignar</html>")
                || vista.getLblDireccion().getText().trim().equalsIgnoreCase("<html>Sin asignar</html>")
                || vista.getLblNombreUsuario().getText().trim().equalsIgnoreCase("")
                || vista.getLblTelefono().getText().trim().equalsIgnoreCase("")
                || vista.getLblDireccion().getText().trim().equalsIgnoreCase("")) {
            String html = "<html><body width='%1s'><h1>Datos no asignados</h1>"
                    + "<p>Por favor, ingrese sus datos antes de realizar el pedido."
                    + "<p>";
            ControladorGeneral.mostrarOptionPane(html, "Ups", 200, JOptionPane.WARNING_MESSAGE);
            return;
        } else if (ControladorListaPedidos.carrito.getCarrito().isEmpty()) {
            String html = "<html><body width='%1s'><h1>Pedido vacío</h1>"
                    + "<p>Parece que su pedido está vacío, "
                    + "agregue almenos un producto a su pedido"
                    + "<p>";
            ControladorGeneral.mostrarOptionPane(html, "Ups", 300, JOptionPane.WARNING_MESSAGE);
            return;
        } else if (isProcesando) {
            String html = "<html><body width='%1s'><h1>Pedido en ejecución</h1>"
                    + "<p>Ya hay un pedido registrado, espere que termine de ser atendido"
                    + "<p>";
            ControladorGeneral.mostrarOptionPane(html, "Ups", 200, JOptionPane.WARNING_MESSAGE);
            return;
        } else if (!ClienteSocket.estadoConexion) {
            String html = "<html><body width='%1s'><h1>Conexión no disponible</h1>"
                    + "<p>Se encontró que el estado de "
                    + "la conexión no está activa"
                    + "<p>";
            ControladorGeneral.mostrarOptionPane(html, "Ups", 200, JOptionPane.ERROR_MESSAGE);
            return;
        }

        Thread thRealizarPedido = new Thread(() -> {
            //Pedido en ejecución
            isProcesando = true;

            //Spiner
            ImageIcon imagen1 = new ImageIcon(getClass().getResource("/recursos/iconos/spinner.gif"));
            ImageIcon imagen2 = new ImageIcon(imagen1.getImage().getScaledInstance(vista.getIconoSpinner().getWidth(), vista.getIconoSpinner().getHeight(), Image.SCALE_DEFAULT));
            SwingUtilities.invokeLater(() -> {
                vista.getIconoSpinner().setIcon(imagen2);
            });

            //Estado del pedido
            vista.getLblEstado().setText("Procesando");

            Pedido pedidoEnviado = crearPedido();
            ControladorClienteSocket.mandarMensaje("realizarPedido");
            ControladorClienteSocket.enviarObjecto(pedidoEnviado);
            Pedido pedidoRecibido = (Pedido) ControladorClienteSocket.recibirObjeto();

            //Lo pintamos en el panel de historial
            ctrlHistorial.agregarNuevo(pedidoRecibido);

            //Reseteamos las variables
            ControladorPanelPedidos.hashPedidosItem.clear();
            ControladorListaPedidos.carrito.getCarrito().removeAll(ControladorListaPedidos.carrito.getCarrito());
            ControladorDatosCliente.resetearDatos();
            vista.getLblEstado().setText("Pedido no registrado");
            ControladorPanelPedidos.panelPedidos.removeAll();
            ControladorPanelPedidos.panelPedidos.revalidate();
            ControladorPanelPedidos.panelPedidos.repaint();

            //Eliminar Spiner
            SwingUtilities.invokeLater(() -> {
                vista.getIconoSpinner().setIcon(null);
            });

            //Pedido terminado
            isProcesando = false;

        });
        thRealizarPedido.setName("Thread-RealizandoPedido");
        thRealizarPedido.start();
    }

    public Pedido crearPedido() {
        Pedido pedido = new Pedido();

        //Cliente
        Cliente cliente = ControladorDatosCliente.getCliente();
        pedido.setCliente(cliente);

        //Estado
        pedido.setEstado("Registrado");

        //Carrito de compras
        CarritoPedido carrito = ControladorListaPedidos.carrito;
        pedido.setCarrito(carrito);

        return pedido;
    }

}
