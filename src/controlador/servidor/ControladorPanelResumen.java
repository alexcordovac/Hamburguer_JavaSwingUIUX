/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.servidor;

import controlador.ControladorGeneral;
import formulario.cliente.PedidoItem;
import formulario.servidor.FormInicio;
import formularios.GenerarDatosRandom;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import modelos.Cliente;
import modelos.Pedido;
import modelos.Producto;
import modelos.Repartidor;

/**
 *
 * @author Alex
 */
public class ControladorPanelResumen {

    private static FormInicio vista;
    private static Pedido pedido;
    private ControladorBloquearJButton controladorBloquearJButton = new ControladorBloquearJButton();
    private GenerarDatosRandom datoRandom = new GenerarDatosRandom();

    public ControladorPanelResumen() {
    }

    public ControladorPanelResumen(FormInicio vista) {
        ControladorPanelResumen.vista = vista;
        iniComponentes();
    }

    private void iniComponentes() {
        /*Función del botón asginar repartidor*/
        vista.getIconoAsignarRepartidor().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //asignamos un repartidor aleatorio
                vista.getTxtRepartidor().setText(datoRandom.generarNombreRepartidor());
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
        
        /*Función del botón asginar repartidor*/
        vista.getIconoAsignarTiempo().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //asignamos tiempo estimado aleatorio
                vista.getTxtTiempoEstimado().setText(datoRandom.generarTiempoEstimado());
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

        /*Función del botón registrar pedido*/
        vista.getBtnRegistrarPedido().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (vista.getTxtRepartidor().getText().isEmpty()) {
                    String html = "<html><body width='%1s'><h1>Repartidor no asignado</h1>"
                            + "<p>Por favor, asigne un repartidor "
                            + "o genere uno aleatoriamente en el icono del engranaje correspondiente."
                            + "<p>";
                    ControladorGeneral.mostrarOptionPane(html, "Ups", 200, JOptionPane.WARNING_MESSAGE);
                    return;
                } else if (vista.getTxtTiempoEstimado().getText().isEmpty()) {
                    String html = "<html><body width='%1s'><h1>Tiempoe estimado no asignado</h1>"
                            + "<p>Por favor, asigne el tiempo estimado "
                            + "o genérelo aleatoriamente en el icono del engranaje correspondiente."
                            + "<p>";
                    ControladorGeneral.mostrarOptionPane(html, "Ups", 200, JOptionPane.WARNING_MESSAGE);
                    return;
                }else if (pedido== null) {
                    String html = "<html><body width='%1s'><h1>Orden no seleccionada</h1>"
                            + "<p>El 'pedido' es nulo, seleccione una solicitud en el panel de ordenes pendientes"
                            + "<p>";
                    ControladorGeneral.mostrarOptionPane(html, "Ups", 200, JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                new Thread(() -> {
                    synchronized (pedido) {
                        Repartidor repartidor = new Repartidor(vista.getTxtRepartidor().getText());
                        pedido.setRepartidor(repartidor);
                        pedido.setTiempoEntrega(vista.getTxtTiempoEstimado().getText());
                        pedido.setEstado("Atendido");
                        pedido.notify();
                    }
                }).start();

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

    public void pintarPedido() {
        if (pedido == null) {
            return;
        }
        controladorBloquearJButton.habilitar();
        restablecerCampos();
        pintarDatosCliente(pedido.getCliente());
        pintarEstado();
        pintarListaPedidos(pedido.getCarrito().getCarrito());
    }

    public void pintarEstado() {
        vista.getLblEstado().setText("Siendo atendido");
    }

    public void pintarDatosCliente(Cliente cliente) {
        vista.getLblNombre().setText("<html>" + cliente.getNombre() + "</html>");
        vista.getLblTelefono().setText("<html>" + cliente.getTelefono() + "</html>");
        vista.getLblDireccion().setText("<html>" + cliente.getDireccion() + "</html>");
    }

    public void pintarListaPedidos(List<Producto> listaProductos) {
        if (listaProductos.isEmpty()) {
            return;
        }

        listaProductos.forEach((v) -> {
            vista.getPanelResumenPedidos().add(new PedidoItem(v));
        });
        vista.getPanelResumenPedidos().revalidate();
        vista.getPanelResumenPedidos().repaint();
    }

    public void restablecerCampos() {
        String defaultText = "<html>Sin asignar</html>";
        vista.getLblNombre().setText(defaultText);
        vista.getLblTelefono().setText(defaultText);
        vista.getLblDireccion().setText(defaultText);
        vista.getLblEstado().setText(defaultText);
        vista.getPanelResumenPedidos().removeAll();
        vista.getPanelResumenPedidos().revalidate();
        vista.getPanelResumenPedidos().repaint();
        vista.getTxtRepartidor().setText("");
        vista.getTxtTiempoEstimado().setText("");
    }

    /*GETTER Y SETTER*/
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        ControladorPanelResumen.pedido = pedido;
    }

}
