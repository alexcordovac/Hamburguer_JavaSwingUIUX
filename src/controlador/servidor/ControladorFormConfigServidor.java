/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.servidor;

import controlador.ControladorGeneral;
import formulario.servidor.FormConfigServidor;
import formulario.servidor.ServidorSocket;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import jiconfont.swing.IconFontSwing;
import recursos.iconos.GoogleMaterialDesignIcons;
import socket.SocketProps;
import utiles.Constantes;

/**
 *
 * @author Alex
 */
public class ControladorFormConfigServidor {

    private FormConfigServidor vista;
    public static ServidorSocket servidorSocket;
    public static SocketProps serverProps;
    private final String dirActual = System.getProperty("user.dir");
    private final String nombreArchivo = "SocketServerProps.dat";

    /*MouseListener*/
    private MouseListener mlDetenerConexionAutomatica;

    public ControladorFormConfigServidor() {
    }

    public ControladorFormConfigServidor(FormConfigServidor vista) {
        this.vista = vista;
        iniComponentes();
        servidorSocket = new ServidorSocket();
        //Se intenta iniciar la conexión
        inicializarProps();
    }

    private void iniComponentes() {
        //Función del botón guardar props
        vista.getjPanel7().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (vista.getTxtDireccion().getText().isEmpty() || vista.getTxtPuerto().getText().isEmpty()) {
                    return;
                }

                if (serverProps == null) {
                    serverProps = new SocketProps();
                }

                //Intentamos asignar los valores al socket props
                try {
                    serverProps.setDIRECCION(vista.getTxtDireccion().getText());
                    serverProps.setPUERTO(Integer.parseInt(vista.getTxtPuerto().getText()));
                } catch (NumberFormatException ex) {
                    String html = "<html><body width='%1s'><h1>Datos no válidos</h1>"
                            + "<p>Por favor, verifique los datos del servidor"
                            + "<p>";
                    ControladorGeneral.mostrarOptionPane(html, "Error", 300, JOptionPane.ERROR_MESSAGE);
                    System.err.println(ex);
                    return;
                }

                //Serializamos
                serializarProps();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ((JPanel) e.getSource()).setBackground(Constantes.COLOR_SECUNDARIO);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((JPanel) e.getSource()).setBackground(Constantes.COLOR_MEDIO);
            }
        });

        //Eventos del panel del estado de conexión al servidor
        MouseListener mlbtnConectar = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                conectarDesconectarServidor();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (ServidorSocket.isRunning) {
                    ((JPanel) e.getSource()).setBackground(new Color(0, 153, 89));
                } else {
                    ((JPanel) e.getSource()).setBackground(new Color(255, 90, 17));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (ServidorSocket.isRunning) {
                    ((JPanel) e.getSource()).setBackground(new Color(0, 181, 105));
                } else {
                    ((JPanel) e.getSource()).setBackground(new Color(255, 55, 0));
                }
            }
        };
        vista.getjBtnEstado().addMouseListener(mlbtnConectar);

    }

    /*Método para controlar el botón conectar y desconectar el cliente*/
    private synchronized void conectarDesconectarServidor() {
        Thread condesth = new Thread(() -> {

            /*Si hay una conexión desconectamos*/
            if (ServidorSocket.isRunning) {
                detenerServidor();
            } else {
                ejecutarServidor();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
            }
            vista.getLblMensajeServidor().setText("");
        });
        condesth.setName("Thread-ConDes");
        condesth.start();
    }

    private synchronized void detenerServidor() {
        if (ServidorSocket.serverSocket != null) {
            try {
                servidorSocket.detenerServidor();
            } catch (IOException ex) {
                System.err.println("Error al detener servidor clase ControladorFormConfigServidor");
            }
        }
        /*Si logró desconectar, pintamos el panel como desconectado*/
        if (!ServidorSocket.isRunning) {
            servidorDesconectadoPanel();
        } else {
            /*Si no logró desconectar, lo ponemos como conectado*/
            servidorConectadoPanel();
        }

    }

    private synchronized void ejecutarServidor() {
        vista.getLblMensajeServidor().setText("Arrancando..");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        /*Si no está conectado, intenamos conectar*/
        try {
            servidorSocket.runServidor(serverProps.getPUERTO());
        } catch (Exception e) {
            vista.getLblMensajeServidor().setText("Error al arrancar servidor...");
            servidorDesconectadoPanel();
            System.err.println(e.getMessage());
            return;
        }
        /*Si logró conectar, pintamos el panel  (boton de estado de conexion) como conectado*/
        servidorConectadoPanel();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        vista.getLblMensajeServidor().setText("");
    }

    private void servidorDesconectadoPanel() {
        vista.getjBtnEstado().setBackground(new Color(255, 55, 0));
        vista.getLblServidorEstadoIcono().setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.REPORT_PROBLEM, 30, Constantes.COLOR_BLANCO));
        vista.getLblServidorEstado().setText("¡Apagado!");
    }

    private void servidorConectadoPanel() {
        vista.getjBtnEstado().setBackground(new Color(0, 181, 105));
        vista.getLblServidorEstadoIcono().setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.DONE, 30, Constantes.COLOR_BLANCO));
        vista.getLblServidorEstado().setText("Corriendo...");
    }

    public void serializarProps() {
        try {
            //Guardar objeto en un archivo
            FileOutputStream file = new FileOutputStream(dirActual + "\\" + nombreArchivo);
            ObjectOutputStream out = new ObjectOutputStream(file);

            //Serialización
            out.writeObject(serverProps);

            out.close();
            file.close();

            //Mensaje de exito
            String html = "<html><body width='%1s'><h1>Guardado correctamente</h1>"
                    + "<p>Los datos de conexión han sido guardados con éxíto."
                    + "<br><br>"
                    + "Intente la conexión con el servidor si aún no está conectado"
                    + "<p>";
            ControladorGeneral.mostrarOptionPane(html, "Error de conexion", 300, JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            String html = "<html><body width='%1s'><h1>Error al guardar</h1>"
                    + "<p>Lo sentimos, ocurrió un error al intentar guardar"
                    + ex.getMessage()
                    + "<p>";
            ControladorGeneral.mostrarOptionPane(html, "Error de conexion", 300, JOptionPane.ERROR_MESSAGE);
            System.err.println("IOException en serializar socket props: " + ex.getMessage());
        }

    }

    public SocketProps deserealizarProps() {
        SocketProps propsTmp = null;
        try {
            //Leer el archivo
            File archivo = new File(dirActual + "\\" + nombreArchivo);
            archivo.createNewFile();

            FileInputStream file = new FileInputStream(archivo);
            //Si el archivo no tiene nada
            if (file.available() == 0) {
                return null;
            }
            ObjectInputStream in = new ObjectInputStream(file);

            // Deserializacion del objeto
            propsTmp = (SocketProps) in.readObject();

            in.close();
            file.close();

        } catch (IOException ex) {
            System.err.println("IOException en deserealizar socket props: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("ClassNotFoundException en deserealizar socket props: " + ex.getMessage());
        }
        return propsTmp;
    }

    public void inicializarProps() {
        Thread iniTh = new Thread(() -> {
            SocketProps props = deserealizarProps();
            if (props != null) {

                //Pintar en los textfields
                SwingUtilities.invokeLater(() -> {
                    vista.getTxtDireccion().setText(props.getDIRECCION());
                    vista.getTxtPuerto().setText(String.valueOf(props.getPUERTO()));
                });

                //Intentamos conectar ya con las props leidas
                serverProps = props;
                ejecutarServidor();

                //Si la conexion no se dió
                if (!ServidorSocket.isRunning) {
                    String html = "<html><body width='%1s'><h1>Error al correr servidor</h1>"
                            + "<p>Intenté encender la conexión del servidor pero no fue satisfactoria"
                            + ", verifique los datos de conexión en el menu de configuración e intente nuevamente"
                            + "<p>";
                    ControladorGeneral.mostrarOptionPane(html, "Error", 300, JOptionPane.ERROR_MESSAGE);
                }
            } else {
                String html = "<html><body width='%1s'><h1>Configurar servidor</h1>"
                        + "<p>Por favor, configure los datos del servidor en el menú"
                        + "de configuración e intente ejecutar el servidor"
                        + "<p>";
                ControladorGeneral.mostrarOptionPane(html, "Aviso", 300, JOptionPane.INFORMATION_MESSAGE);
            }
        });
        iniTh.setName("Thread-iniProps");
        iniTh.start();
    }

}
