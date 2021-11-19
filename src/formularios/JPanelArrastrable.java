/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import java.awt.Point;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Alex
 */
public class JPanelArrastrable extends JPanel implements MouseMotionListener, MouseListener {
    private Point location;
    private MouseEvent pressed;
    
    public JPanelArrastrable() {
        super();
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    int x, y;

    @Override
    public void mousePressed(MouseEvent e) {
       pressed = e;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Window frame = SwingUtilities.getWindowAncestor(this);
        location = frame.getLocation(location);
        int x = location.x - pressed.getX() + e.getX();
        int y = location.y - pressed.getY() + e.getY();
        frame.setLocation(x, y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
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

}
