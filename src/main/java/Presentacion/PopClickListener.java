/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author LacorZ
 */
public class PopClickListener extends MouseAdapter {

    private JTabbedPane jTabbedPane;

    public PopClickListener(JTabbedPane jTabbedPane_Graficos) {
        this.jTabbedPane = jTabbedPane_Graficos;
    }

    public void mousePressed(MouseEvent e) {
        if (e.isPopupTrigger()) {
            doPop(e);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (e.isPopupTrigger()) {
            doPop(e);
        }
    }

    private void doPop(MouseEvent e) {
        JPopupMenu menu = new JPopupMenu();
        menu.removeAll();
        JMenuItem jMenuItem = new JMenuItem("Cerrar");
//        menu.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
////                if (SwingUtilities.isLeftMouseButton(e)) {                    
//                    jTabbedPane.remove(jTabbedPane.getSelectedComponent());
////                }
//            }
//        });
        
        
        jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                if (SwingUtilities.isLeftMouseButton(e)) {
                    jTabbedPane.remove(jTabbedPane.getSelectedComponent());
//                }
            }
        });
        menu.add(jMenuItem);
        menu.show(e.getComponent(), e.getX(), e.getY());

    }
}
