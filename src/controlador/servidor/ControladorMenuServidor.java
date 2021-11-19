/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.servidor;


import formulario.servidor.FormInicio;
import formulario.servidor.FormConfigServidor;
import formulario.servidor.FormPrincipalServidor;
import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import java.util.HashMap;
import recursos.iconos.GoogleMaterialDesignIcons;
import formularios.MenuItem;

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
public class ControladorMenuServidor {

    private final FormPrincipalServidor vista;
    private HashMap<MenuItem, JPanel> menusVistas;
    private MenuItem menuSeleccionado;

    public ControladorMenuServidor(FormPrincipalServidor vista) {
        this.vista = vista;
        iniMenus();
    }

    /*Función para crear y pintar los menus en el panelMenus de la vista principal*/
    private void iniMenus() {
        //menusVistas = new HashMap<>();
        
        //Menu items
        MenuItem menuInicio = new MenuItem(GoogleMaterialDesignIcons.HOME, "Inicio");
        MenuItem menuConfigServidor = new MenuItem(GoogleMaterialDesignIcons.SETTINGS, "Configurar servidor");
        
        
        //Vistas
        FormInicio formInicio = new FormInicio();
        FormConfigServidor formConfigServidor = new FormConfigServidor();
        
        //Los guardamos en el hashmap con su respectivo formulario
        //menusVistas.put(asignarTrabajo, panelTrabajadorLayout);
       //menusVistas.put(recordsObtenidos , formRecordObtenidos);
        
        //Pintamos la lista de menus
        pintarMenuItems(menuInicio, menuConfigServidor);
        
        //test
        //Pinto de color este menu (clickedao) porque es el que se muestra por default al iniciar
        menuInicio.setIsSeleccionado(true);
        menuInicio.setColor();
        menuSeleccionado = menuInicio;
        
        //test
        vista.getPanelCuerpo().add(formInicio, menuInicio.getName());
        vista.getPanelCuerpo().add(formConfigServidor, menuConfigServidor.getName());
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
