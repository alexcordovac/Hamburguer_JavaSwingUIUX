/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cliente;

import formulario.cliente.ClienteSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import modelos.Pedido;

/**
 *
 * @author Alex
 */
public class ControladorClienteSocket {

    public ControladorClienteSocket() {

    }

    /*Función para leer un objeto del flujo de entrada*/
    public static Object recibirObjeto() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(ClienteSocket.socket.getInputStream());
            Object ob = objectInputStream.readObject();
            return ob;
        } catch (NullPointerException e) {
            System.err.println("NullPointerException al recibir objeto");
            return null;
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundExceptional al recibir objeto");
            System.err.println(e.getMessage());
            return null;
        } catch (IOException e) {
            System.err.println("IOException al recibir objeto");
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /*Función para enviar un objeto por el flujo de salida*/
    public static void enviarObjecto(Object obj) {
        try{
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ClienteSocket.socket.getOutputStream());
            objectOutputStream.writeObject(obj);
        } catch (IOException ex) {
            System.err.println("Error en el método eviar objeto de la clase ControladorServidorScoket");
            System.err.println(ex.getMessage());
        }
    }

    public static void mandarMensaje(String mensaje) {
        /*Indicamos al servidor que le mandaremos un nuevo pedido*/
        try{
            ClienteSocket.salida.writeUTF(mensaje);
            ClienteSocket.salida.flush();
        } catch (NullPointerException e) {
            System.err.println("NullPointerException al realizar pedido");
        } catch (IOException e) {
            System.err.println("IOException al realizar pedido");
        }
    }

}
