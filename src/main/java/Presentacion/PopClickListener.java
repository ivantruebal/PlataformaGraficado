/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Presentacion.Interfaz.Main;
import Presentacion.Interfaz.PanelGrafico;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import org.hibernate.Session;
import org.hibernate.Transaction;
import servicios.ActualizadorActivo;
import servicios.database.BBDD;
import servicios.modelos.Analisis;
import servicios.modelos.Usuario;

/**
 *
 * @author LacorZ
 */
public class PopClickListener extends MouseAdapter {

    private JTabbedPane jTabbedPane;
    private ActualizadorActivo actualizadorActivo;

    public PopClickListener(JTabbedPane jTabbedPane_Graficos, ActualizadorActivo aa) {
        this.jTabbedPane = jTabbedPane_Graficos;
        this.actualizadorActivo = aa;
    }

    public JTabbedPane getjTabbedPane() {
        return jTabbedPane;
    }

    public void setjTabbedPane(JTabbedPane jTabbedPane) {
        this.jTabbedPane = jTabbedPane;
    }

    public ActualizadorActivo getActualizadorActivo() {
        return actualizadorActivo;
    }

    public void setActualizadorActivo(ActualizadorActivo actualizadorActivo) {
        this.actualizadorActivo = actualizadorActivo;
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

    /**
     * Metodo que crea, muestra y añade funcionalidad al menu contextual. Esta
     * funcionalidad cerrará tabs y eliminará el analisis guardado en BBDD
     *
     * @param e
     */
    private void doPop(MouseEvent e) {
        JPopupMenu menu = new JPopupMenu();
        menu.removeAll();
        JMenuItem jMenuItem = new JMenuItem("Cerrar");
        jMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    Runnable runnable = new Runnable() {
                        public void run() {
                            Usuario usuarioActual = BBDD.getUsuarioActual();
                            if (usuarioActual != null) {
                                PanelGrafico panelGrafico = (PanelGrafico) jTabbedPane.getSelectedComponent();
                                if (panelGrafico instanceof PanelGrafico) {
                                    Session session = BBDD.getSession();
                                    Usuario usuarioActual1 = BBDD.getUsuarioActual();
                                    Transaction beginTransaction = session.beginTransaction();
                                    try {
                                        Analisis analisis = ((PanelGrafico) panelGrafico).getAnalisis();
                                        usuarioActual1.getSetAnalisis().remove(analisis);
                                        session.merge(usuarioActual1);
                                        System.out.println("Se recoge analisis de la componente panel grafico: " + analisis);
                                        analisis = (Analisis) session.merge(analisis);
                                        System.out.println("Se mergea analisis de la componente panel grafico con la entida de BBDD: " + analisis);
                                        session.delete(analisis);
                                        beginTransaction.commit();
                                        session.clear();
                                    } catch (Exception e) {
                                        Logger.getLogger(PopClickListener.class.getName()).log(Level.SEVERE, null, e);
                                        beginTransaction.rollback();
                                        session.clear();
                                    }
                                    jTabbedPane.remove(panelGrafico);
                                }
                            }
                        }
                    };
                    runnable.run();
                } else {
                    ActualizadorActivo actualizadorActivo1 = getActualizadorActivo();
                    if (actualizadorActivo1 != null) {
                        actualizadorActivo1.cancel(true);
                    }
                    actualizadorActivo1.setPg((PanelGrafico) getjTabbedPane().getSelectedComponent());
                    actualizadorActivo1.run();
                }

            }
        });
        menu.add(jMenuItem);
        menu.show(e.getComponent(), e.getX(), e.getY());

    }
}
