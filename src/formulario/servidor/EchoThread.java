/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulario.servidor;

import controlador.servidor.ControladorServidorSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import modelos.Pedido;

/**
 *
 * @author Alex
 */
public class EchoThread extends Thread {

    public DataInputStream entrada;
    public DataOutputStream salida;
    public Socket socket;
    private ControladorServidorSocket ctrlServidorSocket;

    public EchoThread(Socket clientSocket) {
        socket = clientSocket;
    }

    @Override
    public synchronized void run() {
        String mensajeEntrada = "";
        try {
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());
            ctrlServidorSocket = new ControladorServidorSocket(socket,entrada, salida);
            while (!mensajeEntrada.equals("salir")) {
                mensajeEntrada = entrada.readUTF();

                switch (mensajeEntrada) {
                    case "realizarPedido": {
                        ctrlServidorSocket.procesarPedido();
                        break;
                    }
                    case "ping": {
                        break;
                    }
                    default: {
                        System.out.println("Cliente dice: " + mensajeEntrada);
                        break;
                    }
                }
            }
        } catch (NullPointerException e) {
            System.err.println("NullPointerException en el método run de la clase EchoThread\n" + e.getMessage());
        } catch (SocketException e) {
            System.err.println("Un cliente se ha desconectado");
        } catch (EOFException e) {
            System.err.println("EOFException en el método run de la clase EchoThread\n" + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error método run de la clase EchoThread");
            System.err.println(e);
        }
    }

    public void cerrar() {
        if (socket != null) {
            try {
                salida.writeUTF("salir");
                socket.close();
                entrada.close();
                salida.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar socket y flujos en la clase EchoThread");
            }
        }
    }
}
