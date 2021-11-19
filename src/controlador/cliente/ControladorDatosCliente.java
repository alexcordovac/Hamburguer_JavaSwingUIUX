/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.cliente;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;
import modelos.Cliente;

/**
 * @author Alex Este controlador es instanciado en la clase FormOrdernar, que es
 * donde están los textfields de los datos del cliente. También dentro de el
 * FormPrincipalCliente, que es donde está el menú izquierdo
 */
public class ControladorDatosCliente {

    private static JTextField txtNombre;
    private static JTextField txtTelefono;
    private static JTextArea txtDireccion;

    private static JLabel lblNombre;
    private static JLabel lblTelefono;
    private static JLabel lblDireccion;

    public ControladorDatosCliente() {
    }

    public ControladorDatosCliente(JTextField txtNombre, JTextField txtTelefono, JTextArea txtDireccion) {
        ControladorDatosCliente.txtNombre = txtNombre;
        ControladorDatosCliente.txtTelefono = txtTelefono;
        ControladorDatosCliente.txtDireccion = txtDireccion;
        iniComponentes();
    }

    public ControladorDatosCliente(JLabel lblNombre, JLabel lblTelefono, JLabel lblDireccion) {
        ControladorDatosCliente.lblNombre = lblNombre;
        ControladorDatosCliente.lblTelefono = lblTelefono;
        ControladorDatosCliente.lblDireccion = lblDireccion;
    }
    
    public static Cliente getCliente(){
        Cliente cliente = new Cliente();
        cliente.setNombre(txtNombre.getText());
        cliente.setTelefono(txtTelefono.getText());
        cliente.setDireccion(txtDireccion.getText());
        return cliente;
    }

    private void iniComponentes() {
        /*Document Listener para los campos de los datos del cliente*/
        DocumentListener documentListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                manejarTexto(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                manejarTexto(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        };

        //Agregamos el document listener
        ControladorDatosCliente.txtNombre.getDocument().addDocumentListener(documentListener);
        ControladorDatosCliente.txtTelefono.getDocument().addDocumentListener(documentListener);
        ControladorDatosCliente.txtDireccion.getDocument().addDocumentListener(documentListener);

        //Agregamos el documento para limitar la escritura de caracteres
        ((AbstractDocument)ControladorDatosCliente.txtNombre.getDocument()).setDocumentFilter(new LimitDocumentFilter(26));
        ((AbstractDocument)ControladorDatosCliente.txtTelefono.getDocument()).setDocumentFilter(new LimitDocumentFilter(15));
        ((AbstractDocument)ControladorDatosCliente.txtDireccion.getDocument()).setDocumentFilter(new LimitDocumentFilter(45));
    }

    private void manejarTexto(DocumentEvent e) {
        Document document = e.getDocument();

        if (document == ControladorDatosCliente.txtNombre.getDocument()) {
            //Nombre
            ControladorDatosCliente.lblNombre.setText("<html>" + ControladorDatosCliente.txtNombre.getText() + "</html>");

        } else if (document == ControladorDatosCliente.txtTelefono.getDocument()) {
            //Teléfono
            ControladorDatosCliente.lblTelefono.setText("<html>" + ControladorDatosCliente.txtTelefono.getText() + "</html>");

        } else if (document == ControladorDatosCliente.txtDireccion.getDocument()) {
            //Direccion
            ControladorDatosCliente.lblDireccion.setText("<html>" + ControladorDatosCliente.txtDireccion.getText() + "</html>");
        }
    }
    
    public static void resetearDatos(){
        ControladorDatosCliente.lblNombre.setText("<html>" + ControladorDatosCliente.txtNombre.getText() + "</html>");
        ControladorDatosCliente.lblTelefono.setText("<html>" + ControladorDatosCliente.txtTelefono.getText() + "</html>");
        ControladorDatosCliente.lblDireccion.setText("<html>" + ControladorDatosCliente.txtDireccion.getText() + "</html>");
    }

    public class LimitDocumentFilter extends DocumentFilter {

        private int limit;

        public LimitDocumentFilter(int limit) {
            if (limit <= 0) {
                throw new IllegalArgumentException("El límite no puede ser <= 0");
            }
            this.limit = limit;
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            int currentLength = fb.getDocument().getLength();
            int overLimit = (currentLength + text.length()) - limit - length;
            if (overLimit > 0) {
                text = text.substring(0, text.length() - overLimit);
            }
            if (text.length() > 0) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

    }

}
