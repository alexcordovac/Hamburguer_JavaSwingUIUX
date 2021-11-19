/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulario.cliente;

import controlador.cliente.ControladorClienteSocket;
import controlador.cliente.ControladorFormConfigCliente;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author Alex
 */
public class ClienteSocket implements Runnable {

    public static boolean estadoConexion = false;
    public static Socket socket;
    public static DataInputStream entrada;
    public static DataOutputStream salida;
    public static ControladorClienteSocket controladorClienteSocket;

    public ClienteSocket() {
    }

    /*Método para conectar al servidor*/
    public boolean conectar(String direccion, int puerto) {
        try {
            socket = new Socket(direccion, puerto);
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());
            estadoConexion = true;
        } catch (IOException ex) {
            System.err.println("Error conectando al servidor");
        }
        return estadoConexion;
    }

    public void desconectar() {
        try {
            salida.writeUTF("salir");
            socket.close();
            estadoConexion = false;
        } catch (IOException ex) {
            System.err.println("Cliente ya desconectado");
        }
        estadoConexion = false;
    }

    /*Método para comprobar si el socket cliente sigue conectado al servidor*/
    public boolean ping() {
        String mensajeSalida = "ping";
        try {
            salida.writeUTF(mensajeSalida);
            return true;
        } catch (SocketException e) {
            System.err.println("Error haciendo ping al servidor");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error haciendo ping al servidor");
            e.printStackTrace();
        }
        //Si no se pudo hacer el ping, el estado de la conexión es falso (desconectado)
        estadoConexion = false;
        return false;
    }

    /*Función que sirve para escuchar mensajes del servidor, actualmente no utilizo este método ya que
    me da problemas de sockert header inválido, porque al enviar un objeto desde el servidor, esta función tambiíen
    lo lee, así que no ejecuto este método en un nuevo hilo, pero debería hacerse en el método conectar de esta clase*/
    @Override
    public void run() {
        String mensajeEntrada = "";
        try {
            while (!mensajeEntrada.equals("salir")) {
                mensajeEntrada = entrada.readUTF();

                switch (mensajeEntrada) {
                    case "salir": {
                        ControladorFormConfigCliente.servidorDesconectadoPanel();
                        estadoConexion = false;
                        System.out.println("Servidor dice salir");
                        break;
                    }
                    default: {
                        System.out.println("Servidor dice: " + mensajeEntrada);
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            System.err.println("Cliente desconectado");
        } catch (IOException e) {
            System.err.println("IOException error método run clase ClienteSocket");
            System.err.println(e);
        }
    }

}
