/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulario.cliente;

import controlador.cliente.ControladorDatosCliente;
import controlador.cliente.ControladorMenuCliente;
import controlador.cliente.ControladorPanelPedidos;
import controlador.cliente.ControladorPedidos;
import formularios.JPanelArrastrable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jiconfont.swing.IconFontSwing;
import recursos.iconos.GoogleMaterialDesignIcons;
import utiles.Constantes;

/**
 *
 * @author Alex
 */
public class FormPrincipalCliente extends javax.swing.JFrame {

    /**
     * Creates new form FormPrincipal
     */
    public FormPrincipalCliente() {
        configVentana();
        initComponents();
        pintarIconos();
        
        //Controlador de la acción realizar pedido
        ControladorPedidos controladorPedidos = new ControladorPedidos(this);
        
        //Controlador para el panel de pedidos
        ControladorPanelPedidos controladorPedido = new ControladorPanelPedidos(panelPedidos);
        
        //Controlador para los datos del cliente
        ControladorDatosCliente controladorDatosCliente = new ControladorDatosCliente(lblNombre, lblTelefono, lblDireccion);
    }
    
    /*Método para color iconos en la ventana principal*/
    private void pintarIconos() {
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());

        //Top bar
        lblSistemaIco.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.LAUNCH_DINING, 30, Constantes.COLOR_BLANCO));
        
        iconoUsuario.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ACCOUNT_CIRCLE, 25, Constantes.COLOR_SECUNDARIO));
        iconoDireccion.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PLACE, 25, Constantes.COLOR_SECUNDARIO));
        iconoTelefono.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SMARTPHONE, 25, Constantes.COLOR_SECUNDARIO));
        
        lblIconSalir.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.CLOSE, 30, Constantes.COLOR_BLANCO));
        lblIconMinMax.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.REMOVE, 30, Constantes.COLOR_BLANCO));
        
        //Botón esconder menu
        lblMostrarOcultarMenu.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.NAVIGATE_BEFORE, 50, Constantes.COLOR_LIGERO));
        
        //Icono realizar pedido
        iconoRealizarPedido.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PAID, 40, Constantes.COLOR_BLANCO));
        
        //Estado del pedido
        iconoEstado.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.DELIVERY_DINING, 25, Constantes.COLOR_SECUNDARIO));
        
        //Menu items
        //jLabel2.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ASSIGNMENT_IND, 30, Constantes.COLOR_PRIMARIO));
        //jLabel3.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.EQUALIZER, 30, Constantes.COLOR_PRIMARIO));
    }
    
    /*Ajustes a la ventana principal*/
    private void configVentana() {
        //Ajustar al 90% de la pantalla
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) tk.getScreenSize().getWidth());
        int ySize = ((int) tk.getScreenSize().getHeight());
        int gameHeight = (int) (Math.round(ySize * 0.90));
        int gameWidth = (int) (Math.round(xSize * 0.90));
        setPreferredSize(new Dimension(gameWidth, gameHeight));

        getContentPane().setBackground(new Color(230, 230, 230));
        setResizable(true);
        setUndecorated(true);

        //Redondear bordes
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 40, 40));
            }
        });
    }
    
    /*Función para mostrar o ocultar el menú deslizable de la izquierda*/
    public void mostrarOcultarMenu(JPanel menushowhide) {
        
        //ocultar
        if (menuShowed == true) {
            int width = menushowhide.getWidth();
            new Thread(() -> {
                    for (int i = width; i >= 0; i-=2) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(FormPrincipalCliente.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        menushowhide.setPreferredSize(new Dimension(i, menushowhide.getHeight()));
                        menushowhide.revalidate();
                        menushowhide.repaint();
                }
            }).start();
            this.revalidate();
            lblMostrarOcultarMenu.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.MENU, 40, Constantes.COLOR_LIGERO));
            menuShowed = false;

        } else {
            //mostrar
            int width = menushowhide.getWidth();
            new Thread(() -> {
                    for (int i = width; i <= 300; i+=2) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(FormPrincipalCliente.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        menushowhide.setPreferredSize(new Dimension(i, menushowhide.getHeight()));
                        menushowhide.revalidate();
                        menushowhide.repaint();
                }
            }).start();
            lblMostrarOcultarMenu.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.NAVIGATE_BEFORE, 50, Constantes.COLOR_LIGERO));
            menuShowed = true;
        }
    }
    
    
    /*GETTER y SETTERS para el ControladorMenuCliente*/
    public JPanel getPanelMenuContenedor() {    
        return panelMenuContenedor;
    }

    public JPanel getPanelCuerpo() {
        return panelCuerpo;
    }

    public JLabel getLblDireccion() {
        return lblDireccion;
    }

    public JLabel getLblNombreUsuario() {
        return lblNombre;
    }

    public JLabel getLblTelefono() {
        return lblTelefono;
    }

    public JPanel getPanelPedidos() {
        return panelPedidos;
    }

    public JPanel getBtnRegistrarPedido() {
        return btnRegistrarPedido;
    }

    public JLabel getIconoSpinner() {
        return iconoSpinner;
    }

    public JLabel getLblEstado() {
        return lblEstado;
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings(value = "unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelMenu = new JPanelArrastrable();
        jPanel1 = new javax.swing.JPanel();
        lblMostrarOcultarMenu = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        panelMenuContenedor = new javax.swing.JPanel();
        panelOrden = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        iconoUsuario = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        iconoDireccion = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        iconoTelefono = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        iconoEstado = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        panelPedidos = new javax.swing.JPanel();
        panelRealizarPedido = new javax.swing.JPanel();
        btnRegistrarPedido = new javax.swing.JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(50, 50);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setColor(getBackground());
                graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); //paint background
                graphics.setColor(getForeground());
            }

        };
        jPanel9 = new javax.swing.JPanel();
        iconoRealizarPedido = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        iconoSpinner = new javax.swing.JLabel();
        panelCuerpo = new javax.swing.JPanel();
        panelToolBar = new JPanelArrastrable();
        lblIconSalir = new javax.swing.JLabel();
        lblSistemaNombre = new javax.swing.JLabel();
        lblSistemaIco = new javax.swing.JLabel();
        lblIconMinMax = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelMenu.setBackground(Constantes.COLOR_PRIMARIO       );
        panelMenu.setPreferredSize(new java.awt.Dimension(50, 550));
        panelMenu.setLayout(new java.awt.BorderLayout());

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(50, 50));

        lblMostrarOcultarMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMostrarOcultarMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblMostrarOcultarMenuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblMostrarOcultarMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblMostrarOcultarMenuMouseExited(evt);
            }
        });

        jPanel5.setBackground(Constantes.COLOR_PRIMARIO);
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel5MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMostrarOcultarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(lblMostrarOcultarMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        panelMenu.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        panelMenuContenedor.setBorder(javax.swing.BorderFactory.createEmptyBorder(40, 0, 10, 0));
        panelMenuContenedor.setOpaque(false);
        panelMenuContenedor.setLayout(new javax.swing.BoxLayout(panelMenuContenedor, javax.swing.BoxLayout.Y_AXIS));
        panelMenu.add(panelMenuContenedor, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelMenu, java.awt.BorderLayout.LINE_START);

        panelOrden.setBackground(Constantes.COLOR_BLANCO);
        panelOrden.setPreferredSize(new java.awt.Dimension(300, 550));
        panelOrden.setLayout(new java.awt.BorderLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(309, 140));

        iconoUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconoUsuario.setToolTipText(null);

        lblNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombre.setText("<html>Sin asignar</html>");
        lblNombre.setToolTipText(null);
        lblNombre.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 0, 0, 0));
        lblNombre.setMaximumSize(new java.awt.Dimension(112, 47));

        lblDireccion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDireccion.setText("<html>Sin asignar</html>");
        lblDireccion.setToolTipText(null);
        lblDireccion.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblDireccion.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 0, 0, 0));
        lblDireccion.setMaximumSize(new java.awt.Dimension(2147483647, 103));

        iconoDireccion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconoDireccion.setToolTipText(null);

        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));

        iconoTelefono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconoTelefono.setToolTipText(null);

        lblTelefono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTelefono.setText("<html>Sin asignar</html>");
        lblTelefono.setToolTipText(null);
        lblTelefono.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblTelefono.setMaximumSize(new java.awt.Dimension(112, 47));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(iconoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(iconoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iconoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(iconoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(iconoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(iconoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelOrden.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 0, 20));
        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 70));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Mi pedido");
        jLabel1.setToolTipText(null);

        iconoEstado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblEstado.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblEstado.setText("Pedido no registrado");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(iconoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 54, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconoEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)))
        );

        jPanel4.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel6.setOpaque(false);
        jPanel6.setLayout(new java.awt.BorderLayout());

        panelPedidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(40, 0, 0, 0));
        panelPedidos.setOpaque(false);
        panelPedidos.setLayout(new javax.swing.BoxLayout(panelPedidos, javax.swing.BoxLayout.Y_AXIS));
        jPanel6.add(panelPedidos, java.awt.BorderLayout.CENTER);

        panelRealizarPedido.setOpaque(false);

        btnRegistrarPedido.setBackground(Constantes.COLOR_SECUNDARIO);
        btnRegistrarPedido.setOpaque(false);
        btnRegistrarPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistrarPedidoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrarPedidoMouseExited(evt);
            }
        });
        btnRegistrarPedido.setLayout(new java.awt.BorderLayout());

        jPanel9.setOpaque(false);
        jPanel9.setPreferredSize(new java.awt.Dimension(60, 36));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconoRealizarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconoRealizarPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        btnRegistrarPedido.add(jPanel9, java.awt.BorderLayout.LINE_START);

        jPanel10.setOpaque(false);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setForeground(Constantes.COLOR_BLANCO);
        jLabel2.setText("Realizar pedido");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        btnRegistrarPedido.add(jPanel10, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout panelRealizarPedidoLayout = new javax.swing.GroupLayout(panelRealizarPedido);
        panelRealizarPedido.setLayout(panelRealizarPedidoLayout);
        panelRealizarPedidoLayout.setHorizontalGroup(
            panelRealizarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRealizarPedidoLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(btnRegistrarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iconoSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelRealizarPedidoLayout.setVerticalGroup(
            panelRealizarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRealizarPedidoLayout.createSequentialGroup()
                .addComponent(btnRegistrarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 20, Short.MAX_VALUE))
            .addGroup(panelRealizarPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconoSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.add(panelRealizarPedido, java.awt.BorderLayout.PAGE_END);

        jPanel4.add(jPanel6, java.awt.BorderLayout.CENTER);

        panelOrden.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelOrden, java.awt.BorderLayout.LINE_END);

        panelCuerpo.setBackground(Constantes.COLOR_LIGERO);
        panelCuerpo.setLayout(new java.awt.CardLayout());
        getContentPane().add(panelCuerpo, java.awt.BorderLayout.CENTER);

        panelToolBar.setBackground(Constantes.COLOR_PRIMARIO);
        panelToolBar.setPreferredSize(new java.awt.Dimension(900, 50));

        lblIconSalir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconSalir.setAlignmentX(0.5F);
        lblIconSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblIconSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconSalirMouseClicked(evt);
            }
        });

        lblSistemaNombre.setText(Constantes.EMPRESA + " - Cliente");
        lblSistemaNombre.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblSistemaNombre.setForeground(Constantes.COLOR_BLANCO       );

        lblIconMinMax.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIconMinMax.setAlignmentX(0.5F);
        lblIconMinMax.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lblIconMinMax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIconMinMaxMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelToolBarLayout = new javax.swing.GroupLayout(panelToolBar);
        panelToolBar.setLayout(panelToolBarLayout);
        panelToolBarLayout.setHorizontalGroup(
            panelToolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelToolBarLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblSistemaIco, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSistemaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 476, Short.MAX_VALUE)
                .addComponent(lblIconMinMax, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIconSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelToolBarLayout.setVerticalGroup(
            panelToolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelToolBarLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(panelToolBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblIconSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(lblSistemaNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSistemaIco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIconMinMax, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(panelToolBar, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblIconSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconSalirMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblIconSalirMouseClicked

    private void lblIconMinMaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIconMinMaxMouseClicked
        if(minmax){
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }else{
            this.setExtendedState(JFrame.NORMAL);
        }
        minmax = !minmax;
    }//GEN-LAST:event_lblIconMinMaxMouseClicked
    boolean menuShowed = true;
    private void lblMostrarOcultarMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMostrarOcultarMenuMouseClicked
        mostrarOcultarMenu(this.panelOrden);
    }//GEN-LAST:event_lblMostrarOcultarMenuMouseClicked

    private void lblMostrarOcultarMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMostrarOcultarMenuMouseEntered
        jPanel4.setBackground(Constantes.COLOR_MEDIO);
    }//GEN-LAST:event_lblMostrarOcultarMenuMouseEntered

    private void lblMostrarOcultarMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMostrarOcultarMenuMouseExited
        jPanel4.setBackground(Constantes.COLOR_PRIMARIO);
    }//GEN-LAST:event_lblMostrarOcultarMenuMouseExited

    private void jPanel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseEntered
        jPanel4.setBackground(Constantes.COLOR_MEDIO);
    }//GEN-LAST:event_jPanel5MouseEntered

    private void jPanel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseExited
        jPanel4.setBackground(Constantes.COLOR_PRIMARIO);
    }//GEN-LAST:event_jPanel5MouseExited

    private void btnRegistrarPedidoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarPedidoMouseEntered
        btnRegistrarPedido.setBackground(Constantes.COLOR_PRIMARIO);
    }//GEN-LAST:event_btnRegistrarPedidoMouseEntered

    private void btnRegistrarPedidoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarPedidoMouseExited
        btnRegistrarPedido.setBackground(Constantes.COLOR_SECUNDARIO);
    }//GEN-LAST:event_btnRegistrarPedidoMouseExited
    
    boolean minmax = true;    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPrincipalCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FormPrincipalCliente vistaPrincipal = new FormPrincipalCliente();
                vistaPrincipal.setLocationRelativeTo(null);
                vistaPrincipal.setVisible(true);
                
                ControladorMenuCliente ctrlMenu = new ControladorMenuCliente(vistaPrincipal);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnRegistrarPedido;
    private javax.swing.JLabel iconoDireccion;
    private javax.swing.JLabel iconoEstado;
    private javax.swing.JLabel iconoRealizarPedido;
    private javax.swing.JLabel iconoSpinner;
    private javax.swing.JLabel iconoTelefono;
    private javax.swing.JLabel iconoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblIconMinMax;
    private javax.swing.JLabel lblIconSalir;
    private javax.swing.JLabel lblMostrarOcultarMenu;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblSistemaIco;
    private javax.swing.JLabel lblSistemaNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JPanel panelCuerpo;
    private javax.swing.JPanel panelMenu;
    private javax.swing.JPanel panelMenuContenedor;
    private javax.swing.JPanel panelOrden;
    private javax.swing.JPanel panelPedidos;
    private javax.swing.JPanel panelRealizarPedido;
    private javax.swing.JPanel panelToolBar;
    // End of variables declaration//GEN-END:variables
}
