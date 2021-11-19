/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author Alex
 */
public class SocketProps implements Serializable {

    private int PUERTO = 1978;
    private String DIRECCION = "localhost";

    public SocketProps() {
    }
    
    public InetAddress obtenerDireccion() {
        try {
            return InetAddress.getByName(DIRECCION);
        } catch (UnknownHostException e) {
            System.err.println("UnknownHostException");
        }
        return null;
    }
    
    /*GETTER Y SETTER*/

    public int getPUERTO() {
        return PUERTO;
    }

    public void setPUERTO(int PUERTO) {
        this.PUERTO = PUERTO;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }
    
}
