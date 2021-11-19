/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cliente;


import formulario.cliente.FormConfigCliente;
import formulario.cliente.FormOrdenar;
import formulario.cliente.FormPrincipalCliente;
import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import java.util.HashMap;
import recursos.iconos.GoogleMaterialDesignIcons;
import formularios.MenuItem;
import java.awt.Dimension;
import javax.swing.Box;

/**
 * Controla la vista del cuerpo de la pantalla principal.
 * 1-Primera implementacion guardando las vistas en un hashmap, revalidando y repintando el cuerpo.
 * 2-(actual) Usando cardlayout en el cuerpo de la pantalla principal.
 *   Para eliminar esta opcion, borrar las lineas con el comentario test
 *   Opcionalmente cambiar el layout del panel cuerpo a grid layout con 1 columna
 *   Descomentar aquí en el mouselistener donde se llama el metodo controlarVista
 *   Descomentar en el método controlarVista las líneas comentadas
 * @author Alex
 */
public class ControladorMenuCliente {

    private final FormPrincipalCliente vista;
    private HashMap<MenuItem, JPanel> menusVistas;
    private MenuItem menuSeleccionado;

    public ControladorMenuCliente(FormPrincipalCliente vista) {
        this.vista = vista;
        iniMenus();
    }

    /*Función para crear y pintar los menus en el panelMenus de la vista principal*/
    private void iniMenus() {
        //menusVistas = new HashMap<>();
        
        //Menu items
        MenuItem realizarPedido = new MenuItem(GoogleMaterialDesignIcons.RESTAURANT_MENU, "Realizar pedido");
        MenuItem configConexion = new MenuItem(GoogleMaterialDesignIcons.SETTINGS, "Configurar conexion");
        
        
        //Vistas
        FormOrdenar formOrdenar = new FormOrdenar();
        FormConfigCliente formConfigSocket = new FormConfigCliente();
        
        //Los guardamos en el hashmap con su respectivo formulario
        //menusVistas.put(asignarTrabajo, panelTrabajadorLayout);
       //menusVistas.put(recordsObtenidos , formRecordObtenidos);
        
        //Pintamos la lista de menus
        pintarMenuItems(realizarPedido, configConexion);
        
        //test
        //Pinto de color este menu (clickedao) porque es el que se muestra por default al iniciar
        realizarPedido.setIsSeleccionado(true);
        realizarPedido.setColor();
        menuSeleccionado = realizarPedido;
        
        //test
        vista.getPanelCuerpo().add(formOrdenar, realizarPedido.getName());
        vista.getPanelCuerpo().add(formConfigSocket, configConexion.getName());
        vista.getPanelCuerpo().revalidate();
        vista.getPanelCuerpo().repaint();
        
    }
    
    private void pintarMenuItems(MenuItem... menu) {
        JPanel panelMenus = vista.getPanelMenuContenedor();
        MouseListener mouseListener = iniMouseListener();
        for (int i = 0; i < menu.length; i++) {
            panelMenus.add(menu[i]);
            menu[i].addMouseListener(mouseListener);
        }
        panelMenus.revalidate();
        panelMenus.repaint();
    }

    private MouseListener iniMouseListener() {
        MouseListener mouseListenerPanel = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controlarVista(e);
                
                //test
                ((CardLayout) vista.getPanelCuerpo().getLayout()).show(vista.getPanelCuerpo(), ((MenuItem) e.getSource()).getName());
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
        };

        return mouseListenerPanel;
    }

    private void controlarVista(MouseEvent e) {
        MenuItem tmp = (MenuItem) e.getSource();

        if (tmp != this.menuSeleccionado) {

            if (this.menuSeleccionado != null) {
                this.menuSeleccionado.setIsSeleccionado(false);
                this.menuSeleccionado.resetColor();
            }

            tmp.setColor();
            tmp.setIsSeleccionado(true);
            this.menuSeleccionado = tmp;

            //Si el menu ya está registrado en el hashmap con su JPanel lo traemos y lo pintamos
//            JPanel hashFound = this.menusVistas.get(tmp);
//            if (hashFound != null) {
//                JPanel panel = vista.getPanelCuerpo();
//                panel.removeAll();
//                panel.add(hashFound);
//                panel.repaint();
//                panel.revalidate();
//            }
        }
    }

}
