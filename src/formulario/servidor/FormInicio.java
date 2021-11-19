/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulario.servidor;

import controlador.servidor.ControladorPanelPedidosEntrantes;
import controlador.servidor.ControladorPanelResumen;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import jiconfont.IconCode;
import jiconfont.swing.IconFontSwing;
import recursos.iconos.GoogleMaterialDesignIcons;
import utiles.Constantes;
import utiles.TextPrompt;
import utiles.WrapLayout;

/**
 *
 * @author Alex
 */
public class FormInicio extends javax.swing.JPanel {

    /**
     * Creates new form FormOrdenar
     */
    public FormInicio() {
        initComponents();
        pintarIconos();

        /*Velocidad del jscrollpane*/
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(20);
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(20);
        
        //Layout del panel de pedidos entrantes
        panelPedidosEntrantes.setLayout(new WrapLayout(FlowLayout.LEFT, 15, 15));
        
        //Controlador para el panel de pedidos entrantes
        ControladorPanelPedidosEntrantes controladorPanelPedidosEntrantes = new ControladorPanelPedidosEntrantes(panelPedidosEntrantes);
        
        //Controlador del panel resúmen de los pedidos
        ControladorPanelResumen controladorPanelResumen = new ControladorPanelResumen(this);
    }

    private void pintarIconos() {
        //Icono de búsqueda
        iconoBusqueda.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SEARCH, 20, Constantes.COLOR_PRIMARIO));

        //Icono Filtrar
        iconoFiltrar.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.FILTER_LIST, 20, Constantes.COLOR_PRIMARIO));

        //Iconos de datos del cliente
        iconoUsuario.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.ACCOUNT_CIRCLE, 25, Constantes.COLOR_SECUNDARIO));
        iconoDireccion.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PLACE, 25, Constantes.COLOR_SECUNDARIO));
        iconoTelefono.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SMARTPHONE, 25, Constantes.COLOR_SECUNDARIO));

        //Repartido y tiempo estimado
        iconoRepartidor.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PERSON, 25, Constantes.COLOR_SECUNDARIO));
        iconoAsignarRepartidor.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SETTINGS, 20, Constantes.COLOR_SECUNDARIO));
        iconoTiempoEstimado.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SCHEDULE, 25, Constantes.COLOR_SECUNDARIO));
        iconoAsignarTiempo.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.SETTINGS, 20, Constantes.COLOR_SECUNDARIO));
        //Estado del pedido
        iconoEstado.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.DELIVERY_DINING, 25, Constantes.COLOR_SECUNDARIO));

        //Icono realizar pedido
        iconoRealizarPedido.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.PAID, 40, Constantes.COLOR_BLANCO));

    }

    private void categoriaHover(JPanel panel, JLabel icono, IconCode icoCode, JLabel cat) {
        Color color = panel.getBackground();
        if (color.equals(Constantes.COLOR_PRIMARIO)) {
            return;
        }

        if (color.equals(Constantes.COLOR_BLANCO)) {
            panel.setBackground(Constantes.COLOR_MEDIO);
            icono.setIcon(IconFontSwing.buildIcon(icoCode, 25, Constantes.COLOR_BLANCO));
            cat.setForeground(Constantes.COLOR_BLANCO);
        } else {
            panel.setBackground(Constantes.COLOR_BLANCO);
            icono.setIcon(IconFontSwing.buildIcon(icoCode, 25, new Color(184, 183, 180)));
            cat.setForeground(new Color(184, 183, 180));
        }
    }

    /*GETTER Y SETTERS*/

    public JLabel getLblDireccion() {
        return lblDireccion;
    }

    public JLabel getLblEstado() {
        return lblEstado;
    }

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public JLabel getLblTelefono() {
        return lblTelefono;
    }

    public JPanel getPanelPedidosEntrantes() {
        return panelPedidosEntrantes;
    }

    public JPanel getPanelResumenPedidos() {
        return panelResumenPedidos;
    }

    public JTextField getTxtRepartidor() {
        return txtRepartidor;
    }

    public JTextField getTxtTiempoEstimado() {
        return txtTiempoEstimado;
    }

    public JLabel getIconoAsignarRepartidor() {
        return iconoAsignarRepartidor;
    }

    public JLabel getIconoAsignarTiempo() {
        return iconoAsignarTiempo;
    }

    public JPanel getBtnRegistrarPedido() {
        return btnRegistrarPedido;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(30, 30);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setColor(getBackground());
                graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); //paint background

            }
        };
        iconoBusqueda = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = new Dimension(30, 30);
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                graphics.setColor(getBackground());
                graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height); //paint background

            }
        };
        iconoFiltrar = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel(){
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

            }
        };
        jPanel6 = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        iconoUsuario = new javax.swing.JLabel();
        iconoTelefono = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        iconoDireccion = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel9 = new javax.swing.JPanel();
        iconoEstado = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        panelResumenPedidos = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        iconoRepartidor = new javax.swing.JLabel();
        txtRepartidor = new javax.swing.JTextField();
        iconoAsignarRepartidor = new javax.swing.JLabel();
        iconoAsignarTiempo = new javax.swing.JLabel();
        txtTiempoEstimado = new javax.swing.JTextField();
        iconoTiempoEstimado = new javax.swing.JLabel();
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
        jPanel10 = new javax.swing.JPanel();
        iconoRealizarPedido = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        iconoSpinner = new javax.swing.JLabel();
        jpanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelPedidosEntrantes = new javax.swing.JPanel(){
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

            }
        };

        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 30, 30, 30));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(950, 50));

        jPanel4.setBackground(Constantes.COLOR_BLANCO);
        jPanel4.setOpaque(false);

        iconoBusqueda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jTextField1.setEditable(false);
        jTextField1.setBackground(new Color(0, 0, 0, 0));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(204, 204, 204));
        jTextField1.setText("Buscar");
        jTextField1.setBorder(null);
        jTextField1.setEnabled(false);
        jTextField1.setFocusable(false);
        jTextField1.setOpaque(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(iconoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconoBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        jPanel5.setBackground(Constantes.COLOR_BLANCO);
        jPanel5.setOpaque(false);

        iconoFiltrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jTextField2.setEditable(false);
        jTextField2.setBackground(new Color(0, 0, 0, 0));
        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(204, 204, 204));
        jTextField2.setText("Filtrar");
        jTextField2.setBorder(null);
        jTextField2.setEnabled(false);
        jTextField2.setFocusable(false);
        jTextField2.setOpaque(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(iconoFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconoFiltrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(654, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 0, 0));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(Constantes.COLOR_BLANCO);
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 500));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.Y_AXIS));

        jPanel6.setBackground(new java.awt.Color(255, 0, 51));
        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        jPanel6.setMaximumSize(new java.awt.Dimension(32767, 180));
        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(300, 190));

        lblNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombre.setText("<html>Sin asignar</html>");
        lblNombre.setToolTipText(null);
        lblNombre.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 0, 0, 0));
        lblNombre.setMaximumSize(new java.awt.Dimension(112, 47));

        iconoUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconoUsuario.setToolTipText(null);

        iconoTelefono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconoTelefono.setToolTipText(null);

        lblTelefono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTelefono.setText("<html>Sin asignar</html>");
        lblTelefono.setToolTipText(null);
        lblTelefono.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblTelefono.setMaximumSize(new java.awt.Dimension(112, 47));

        lblDireccion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblDireccion.setText("<html>Sin asignar</html>");
        lblDireccion.setToolTipText(null);
        lblDireccion.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblDireccion.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 0, 0, 0));
        lblDireccion.setMaximumSize(new java.awt.Dimension(2147483647, 103));

        iconoDireccion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconoDireccion.setToolTipText(null);

        jSeparator1.setForeground(new java.awt.Color(204, 204, 204));

        jPanel9.setOpaque(false);
        jPanel9.setPreferredSize(new java.awt.Dimension(300, 70));

        iconoEstado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lblEstado.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblEstado.setText("Sin asignar");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(iconoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 54, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconoEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Pedido");
        jLabel1.setToolTipText(null);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(iconoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(iconoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(iconoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(iconoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(iconoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(iconoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.add(jPanel6);

        jPanel7.setBackground(new java.awt.Color(0, 0, 153));
        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(300, 200));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBorder(null);
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setOpaque(false);

        panelResumenPedidos.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 0, 5));
        panelResumenPedidos.setOpaque(false);
        panelResumenPedidos.setLayout(new javax.swing.BoxLayout(panelResumenPedidos, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane2.setViewportView(panelResumenPedidos);

        jScrollPane2.getViewport().setOpaque(false);
        jScrollPane2.setViewportBorder(null);
        JScrollBar vertical2 = jScrollPane2.getVerticalScrollBar();
        vertical2.setPreferredSize( new Dimension(0,0) );

        jPanel7.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel7);

        jPanel8.setBackground(new java.awt.Color(153, 153, 153));
        jPanel8.setMaximumSize(new java.awt.Dimension(32767, 150));
        jPanel8.setOpaque(false);
        jPanel8.setPreferredSize(new java.awt.Dimension(300, 170));

        txtRepartidor.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtRepartidor.setOpaque(false);

        txtTiempoEstimado.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtTiempoEstimado.setOpaque(false);

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

        jPanel10.setOpaque(false);
        jPanel10.setPreferredSize(new java.awt.Dimension(60, 36));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconoRealizarPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconoRealizarPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        btnRegistrarPedido.add(jPanel10, java.awt.BorderLayout.LINE_START);

        jPanel11.setOpaque(false);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(Constantes.COLOR_BLANCO);
        jLabel6.setText("Envíar pedido");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        btnRegistrarPedido.add(jPanel11, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(btnRegistrarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iconoSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(iconoRepartidor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRepartidor, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(iconoAsignarRepartidor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(iconoTiempoEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTiempoEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(iconoAsignarTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(19, 19, 19))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconoAsignarRepartidor, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(iconoRepartidor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtRepartidor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconoAsignarTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(iconoTiempoEstimado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTiempoEstimado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegistrarPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(iconoSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19))
        );

        TextPrompt placeHolderRepartidor= new TextPrompt("Repartidor", txtRepartidor);
        placeHolderRepartidor.changeAlpha(0.75f);
        placeHolderRepartidor.changeStyle(Font.ITALIC);
        TextPrompt placeHolderTE= new TextPrompt("Tiempo estimado", txtTiempoEstimado);
        placeHolderTE.changeAlpha(0.75f);
        placeHolderTE.changeStyle(Font.ITALIC);

        jPanel3.add(jPanel8);

        jPanel2.add(jPanel3, java.awt.BorderLayout.LINE_END);

        jpanel4.setBackground(Constantes.COLOR_BLANCO);
        jpanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 30));
        jpanel4.setOpaque(false);
        jpanel4.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setOpaque(false);

        panelPedidosEntrantes.setBackground(Constantes.COLOR_BLANCO);
        panelPedidosEntrantes.setOpaque(false);

        javax.swing.GroupLayout panelPedidosEntrantesLayout = new javax.swing.GroupLayout(panelPedidosEntrantes);
        panelPedidosEntrantes.setLayout(panelPedidosEntrantesLayout);
        panelPedidosEntrantesLayout.setHorizontalGroup(
            panelPedidosEntrantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );
        panelPedidosEntrantesLayout.setVerticalGroup(
            panelPedidosEntrantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelPedidosEntrantes);

        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setViewportBorder(null);
        JScrollBar vertical = jScrollPane1.getVerticalScrollBar();
        vertical.setPreferredSize( new Dimension(0,0) );

        jpanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jpanel4, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarPedidoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarPedidoMouseEntered
        btnRegistrarPedido.setBackground(Constantes.COLOR_PRIMARIO);
    }//GEN-LAST:event_btnRegistrarPedidoMouseEntered

    private void btnRegistrarPedidoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarPedidoMouseExited
        btnRegistrarPedido.setBackground(Constantes.COLOR_SECUNDARIO);
    }//GEN-LAST:event_btnRegistrarPedidoMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnRegistrarPedido;
    private javax.swing.JLabel iconoAsignarRepartidor;
    private javax.swing.JLabel iconoAsignarTiempo;
    private javax.swing.JLabel iconoBusqueda;
    private javax.swing.JLabel iconoDireccion;
    private javax.swing.JLabel iconoEstado;
    private javax.swing.JLabel iconoFiltrar;
    private javax.swing.JLabel iconoRealizarPedido;
    private javax.swing.JLabel iconoRepartidor;
    private javax.swing.JLabel iconoSpinner;
    private javax.swing.JLabel iconoTelefono;
    private javax.swing.JLabel iconoTiempoEstimado;
    private javax.swing.JLabel iconoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JPanel jpanel4;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JPanel panelPedidosEntrantes;
    private javax.swing.JPanel panelResumenPedidos;
    private javax.swing.JTextField txtRepartidor;
    private javax.swing.JTextField txtTiempoEstimado;
    // End of variables declaration//GEN-END:variables
}
