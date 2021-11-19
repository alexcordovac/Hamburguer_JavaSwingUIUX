/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.servidor;

import formulario.servidor.ItemSolicitudPedido;
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
public class ControladorServidorSocket {

    private Socket socket;
    private DataInputStream entrada;
    private DataOutputStream salida;
    private ControladorPanelPedidosEntrantes ctrlPanelPedidosEntrantes;
    private ControladorPanelResumen ctrlPanelResumen;
    public ControladorServidorSocket() {
        ctrlPanelPedidosEntrantes = new ControladorPanelPedidosEntrantes();
        ctrlPanelResumen = new ControladorPanelResumen();
    }

    public ControladorServidorSocket(Socket socket, DataInputStream entrada, DataOutputStream salida) {
        this();
        this.socket = socket;
        this.entrada = entrada;
        this.salida= salida;
    }

    /*Función para llevar todo el proceso de recibir un pedido por el cliente y reenviárselo*/
    public boolean procesarPedido() {
        try {
            //Recibimos el pedido
            Pedido recibido = (Pedido) recibirObjeto();

            //Lo pitamos en el panel de pedidos entrantes
            ctrlPanelPedidosEntrantes.pintarNuevoItem(recibido);
            
            //Detenemos el hilo hasta que se pulse el botón enviar pedido y notifique a este objeto
            synchronized (recibido) {
                try {
                    recibido.wait();
                } catch (InterruptedException e) {
                    System.err.println("Hilo procesarPedido interrumpido");
                }
            }
            
            //Lo eliminamos el panel de pedidos entrantes y reseteamos los campos
            ctrlPanelPedidosEntrantes.eliminarItem(recibido);
            ctrlPanelResumen.restablecerCampos();
            ctrlPanelResumen.setPedido(null);
            
            enviarObjecto(recibido);
            return true;
        } catch (Exception e) {
            System.err.println("Error al procesar pedido");
            System.err.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
        
    }

    private void iniFlujos() {
        try {
            entrada = new DataInputStream(socket.getInputStream());
            salida = new DataOutputStream(socket.getOutputStream());
        } catch (NullPointerException e) {
            System.err.println("NullPointerException error al inicializar flujos, socket no inicializado");
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException error al inicializar los flujos");
            System.err.println(e.getMessage());
        }
    }

    /*Función para leer un objeto del flujo de entrada*/
    private Object recibirObjeto() {
        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
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
            return null;
        }
    }

    /*Función para enviar un objeto por el flujo de salida*/
    private void enviarObjecto(Object obj) {
        try{
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(obj);
        } catch (IOException ex) {
            System.err.println("Error en el método eviar objeto de la clase ControladorServidorScoket");
            System.err.println(ex.getMessage());
        }
    }

}
