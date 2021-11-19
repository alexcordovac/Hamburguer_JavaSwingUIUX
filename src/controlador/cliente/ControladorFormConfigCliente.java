/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cliente;

import controlador.ControladorGeneral;
import formulario.cliente.ClienteSocket;
import formulario.cliente.FormConfigCliente;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.Timer;
import jiconfont.swing.IconFontSwing;
import recursos.iconos.GoogleMaterialDesignIcons;
import socket.SocketProps;
import utiles.Constantes;

/**
 *
 * @author Alex
 */
public class ControladorFormConfigCliente {

    private static FormConfigCliente vista;
    private ClienteSocket clienteSocket = new ClienteSocket();
    public static SocketProps socketProps;
    private final String dirActual = System.getProperty("user.dir");
    private final String nombreArchivo = "SocketClienteProps.dat";

    /*MouseListener*/
    private MouseListener mlDetenerConexionAutomatica;

    /*Timers*/
    //Timer para conectar automáticamente al servidor al inicio del programa
    Timer timerConectar;
    //Timner para hacer un "ping" al servidor y comproba la conexión si sigue activa
    Timer timerTestConexion;

    public ControladorFormConfigCliente() {
    }

    public ControladorFormConfigCliente(FormConfigCliente vista) {
        this.vista = vista;
        iniComponentes();

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

                if (socketProps == null) {
                    socketProps = new SocketProps();
                }

                //Intentamos asignar los valores al socket props
                try {
                    socketProps.setDIRECCION(vista.getTxtDireccion().getText());
                    socketProps.setPUERTO(Integer.parseInt(vista.getTxtPuerto().getText()));
                } catch (NumberFormatException ex) {
                    String html = "<html><body width='%1s'><h1>Datos no válidos</h1>"
                            + "<p>Por favor, verifique los datos de la conexión"
                            + "<p>";
                    ControladorGeneral.mostrarOptionPane(html, "Error", 300, JOptionPane.ERROR_MESSAGE);
                    System.err.println(ex);
                    return;
                }

                //Serializamos
                serializarProps();

                //Intentamos conectar cada 5s
                iniConexion();
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
                conectarDesconectarCliente();

                /*Activamos botón registrar y limpiamos el formulario*/
                ControladorPedidos.vista.getBtnRegistrarPedido().setEnabled(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (ClienteSocket.estadoConexion) {
                    ((JPanel) e.getSource()).setBackground(new Color(0, 153, 89));
                } else {
                    ((JPanel) e.getSource()).setBackground(new Color(255, 90, 17));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (ClienteSocket.estadoConexion) {
                    ((JPanel) e.getSource()).setBackground(new Color(0, 181, 105));
                } else {
                    ((JPanel) e.getSource()).setBackground(new Color(255, 55, 0));
                }
            }
        };
        vista.getjBtnEstado().addMouseListener(mlbtnConectar);

        /*Eventos del botón detener conexión automática*/
        mlDetenerConexionAutomatica = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (timerConectar != null) {
                    timerConectar.stop();
                }
                vista.getLblMensajeServidor().setText("");
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
        vista.getLblDetenerConexionAutomaticaIcono().addMouseListener(mlDetenerConexionAutomatica);

    }

    /*Método para controlar el botón conectar y desconectar el cliente*/
    private synchronized void conectarDesconectarCliente() {
        new Thread(() -> {
            /*Si hay una conexión desconectamos*/
            if (ClienteSocket.estadoConexion) {
                desconectarCliente();
            } else {
                conectarCliente();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
            vista.getLblMensajeServidor().setText("");
        }).start();

    }

    private synchronized void desconectarCliente() {
        clienteSocket.desconectar();
        /*Si logró desconectar, pintamos el panel como desconectado*/
        if (!ClienteSocket.estadoConexion) {
            servidorDesconectadoPanel();
        } else {
            /*Si no logró desconectar, lo ponemos como conectado*/
            servidorConectadoPanel();
        }

    }

    private synchronized void conectarCliente() {
        vista.getLblMensajeServidor().setText("Conectando...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        /*Si no está conectado, intenamos conectar*/
        if (clienteSocket.conectar(socketProps.getDIRECCION(), socketProps.getPUERTO())) {
            /*Si logró conectar, pintamos el panel  (boton de estado de conexion) como conectado*/
            servidorConectadoPanel();

            /*Si el timer está intentado conectar también lo detenemos*/
            if (timerConectar != null) {
                timerConectar.stop();
            }

            /*Quitamos el botón detener conexión automática*/
            vista.getLblDetenerConexionAutomaticaIcono().setIcon(null);

            /*Prueba de conexión cada 10s (nunca se termina de repetir)*/
            testConexion();

        } else {
            vista.getLblMensajeServidor().setText("Error al conectar...");
            servidorDesconectadoPanel();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
        }
        vista.getLblMensajeServidor().setText("");
    }

    /*Método para testear la conexión cada 10 segundos, a partir desde 
    que se haya conectado correctamente el cliente al servidor*/
    private void testConexion() {

        timerTestConexion = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*Si el ping no sale bien, se desconecta*/
                if(ClienteSocket.socket.isClosed()){
                    timerTestConexion.stop();
                    return;
                }
                boolean isPing = false;
                try {
                    isPing = clienteSocket.ping();
                } catch (Exception ex) {
                }
                if (!isPing) {
                    desconectarCliente();
                    ClienteSocket.estadoConexion = false;
                    timerTestConexion.stop();
                }
            }
        });
        timerTestConexion.start();
    }

    private void iniConexion() {
        //Si ya hay conexión pues salimos
        if (ClienteSocket.estadoConexion) {
            return;
        }
        vista.getLblMensajeServidor().setText("Intentando conectar en 5s");
        
        timerConectar = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread newTh = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        //Intenamos conectar
                        conectarCliente();

                        //Si se conectó
                        if (ClienteSocket.estadoConexion) {
                            timerConectar.stop();
                        }
                    }
                });
                newTh.start();
            }
        });
        timerConectar.start();
    }

    public static void servidorDesconectadoPanel() {
        vista.getjBtnEstado().setBackground(new Color(255, 55, 0));
        vista.getLblServidorEstadoIcono().setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.REPORT_PROBLEM, 30, Constantes.COLOR_BLANCO));
        vista.getLblServidorEstado().setText("¡Desconectado!");
    }

    private void servidorConectadoPanel() {
        vista.getjBtnEstado().setBackground(new Color(0, 181, 105));
        vista.getLblServidorEstadoIcono().setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.DONE, 30, Constantes.COLOR_BLANCO));
        vista.getLblServidorEstado().setText("Conectado...");
    }

    public void serializarProps() {
        try {
            //Guardar objeto en un archivo
            FileOutputStream file = new FileOutputStream(dirActual + "\\" + nombreArchivo);
            ObjectOutputStream out = new ObjectOutputStream(file);

            //Serialización
            out.writeObject(socketProps);

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
        new Thread(() -> {
            SocketProps props = deserealizarProps();
            if (props != null) {

                //Pintar en los textfields
                SwingUtilities.invokeLater(() -> {
                    vista.getTxtDireccion().setText(props.getDIRECCION());
                    vista.getTxtPuerto().setText(String.valueOf(props.getPUERTO()));
                });

                //Intentamos conectar ya con las props leidas
                socketProps = props;
                conectarCliente();

                //Si la conexion no se dió
                if (!ClienteSocket.estadoConexion) {
                    String html = "<html><body width='%1s'><h1>Error al intentar conexión</h1>"
                            + "<p>Intenté establecer una conexión con el servidor pero no fue satisfactoria"
                            + ", verifique los datos de conexión en el menu de configuración e intente nuevamente"
                            + "<p>";
                    ControladorGeneral.mostrarOptionPane(html, "Error de conexion", 300, JOptionPane.ERROR_MESSAGE);
                }
            } else {
                String html = "<html><body width='%1s'><h1>Configurar conexión</h1>"
                        + "<p>Por favor, configure los datos de conexión en el menú"
                        + "de configuración e intente conectar al servidor"
                        + "<p>";
                ControladorGeneral.mostrarOptionPane(html, "Aviso", 300, JOptionPane.WARNING_MESSAGE);
            }
        }).start();

    }

}
