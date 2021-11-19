/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulario.servidor;

import controlador.ControladorGeneral;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Alex
 */
public class ServidorSocket {

    public static boolean isRunning = false;
    public static ServerSocket serverSocket;
    private static Thread servidorTh;
    public static List<EchoThread> listaClientes;

    public ServidorSocket() {
        listaClientes = new ArrayList<>();
    }

    public synchronized void runServidor(int puerto) {
        servidorTh = new Thread(() -> {
            Socket socket = null;

            try {
                serverSocket = new ServerSocket(puerto);

                String html = "<html><body width='%1s'><h1>Ejución exitosa</h1>"
                        + "<p>Servidor corriendo en el puerto " + puerto
                        + "<p>";
                ControladorGeneral.mostrarOptionPane(html, "Error", 300, JOptionPane.INFORMATION_MESSAGE);

                isRunning = true;
                while (true) {
                    try {
                        socket = serverSocket.accept();
                        System.out.println("Nuevo cliente conectado satisfactoriamente: " + socket.getRemoteSocketAddress().toString());
                    } catch (IOException e) {
                        //Sin este break, la aplicación crashea ya que se crean muchísimos hilos
                        break;
                    }

                    // nuevo hilo para el cliente
                    EchoThread echoThread = new EchoThread(socket);
                    listaClientes.add(echoThread);
                    echoThread.start();

                }
            } catch (IOException e) {
                String html = "<html><body width='%1s'><h1>IOException</h1>"
                        + "<p>Error al inicializar el socket servidor. "
                        + "Puede que ya haya otra instancia en ejecución"
                        + "<p>";
                ControladorGeneral.mostrarOptionPane(html, "Error", 300, JOptionPane.ERROR_MESSAGE);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ServidorSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(1);
            } finally {
                if (serverSocket != null)
                    try {
                    serverSocket.close();
                    isRunning = false;
                } catch (IOException ex) {
                    System.err.println("IOException al cerrar servidor");
                }
            }
        });
        servidorTh.setName("Thread-Servidor");
        servidorTh.start();
    }

    public void detenerServidor() throws IOException {
        if (servidorTh == null) {
            return;
        }

        isRunning = false;
        try {
            if (serverSocket != null) {
                serverSocket.close();
                for (EchoThread echo : listaClientes) {
                    echo.cerrar();
                }
                listaClientes.removeAll(listaClientes);
            }
        } catch (IOException e) {
            System.err.println("Error al detener servidor");
        }

        servidorTh = null;
    }
}
