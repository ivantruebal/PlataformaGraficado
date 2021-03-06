/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Interfaz;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import servicios.database.BBDD;
import servicios.modelos.Activo;
import servicios.modelos.ListaDeActivos;
import servicios.utils.Utils;

/**
 *
 * @author LacorZ
 */
public class ActiveListWindow extends javax.swing.JFrame {

    /**
     * Creates new form ActiveListWindow
     */
    private ListaDeActivos listaDeActivos;
    private final CRUDWindow parentCrudWindow;
    private boolean esCreacion = true;

    ActiveListWindow(ListaDeActivos listaDeActivos, CRUDWindow crudWindow) {
        initComponents();
        Utils.generalSettings(this);
        estableceEntidad(listaDeActivos);
        this.parentCrudWindow = crudWindow;
        if (this.listaDeActivos != null) {
            this.esCreacion = false;
            jTextField_nombre.setText(this.listaDeActivos.getNombre());
        }
        llenaListas();
        activaBotonGuardado();
    }

    private void estableceEntidad(ListaDeActivos entidad) {
        if (entidad == null) {
            this.listaDeActivos = new ListaDeActivos();
        } else {
            this.listaDeActivos = (ListaDeActivos) entidad;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList_ActivosNoAñadidos = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList_ActivosAñadidos = new javax.swing.JList<>();
        jButton_AñadirActivo = new javax.swing.JButton();
        jButton_RetirarActivo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField_nombre = new javax.swing.JTextField();
        jButton_guardar = new javax.swing.JButton();
        jButton_cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(jList_ActivosNoAñadidos);

        jScrollPane2.setViewportView(jList_ActivosAñadidos);

        jButton_AñadirActivo.setText(">");
        jButton_AñadirActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AñadirActivoActionPerformed(evt);
            }
        });

        jButton_RetirarActivo.setText("<");
        jButton_RetirarActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RetirarActivoActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre:");

        jTextField_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_nombreKeyReleased(evt);
            }
        });

        jButton_guardar.setText("Guardar");
        jButton_guardar.setEnabled(false);
        jButton_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_guardarActionPerformed(evt);
            }
        });

        jButton_cancelar.setText("Cancelar");
        jButton_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_nombre))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_AñadirActivo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton_RetirarActivo, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jButton_guardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_cancelar)
                .addGap(93, 93, 93))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton_AñadirActivo)
                        .addGap(51, 51, 51)
                        .addComponent(jButton_RetirarActivo)
                        .addGap(55, 55, 55)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_guardar)
                    .addComponent(jButton_cancelar))
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_AñadirActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AñadirActivoActionPerformed
        // TODO add your handling code here:
        mueveActivoDeLista(jList_ActivosNoAñadidos, jList_ActivosAñadidos);
    }//GEN-LAST:event_jButton_AñadirActivoActionPerformed

    private void jButton_RetirarActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RetirarActivoActionPerformed
        // TODO add your handling code here:
        mueveActivoDeLista(jList_ActivosAñadidos, jList_ActivosNoAñadidos);
    }//GEN-LAST:event_jButton_RetirarActivoActionPerformed

    private void jButton_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_cancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton_cancelarActionPerformed

    private void jButton_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_guardarActionPerformed
        // TODO add your handling code here:
        guardarListaEnBBDD();

    }//GEN-LAST:event_jButton_guardarActionPerformed

    private void jTextField_nombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_nombreKeyReleased
        // TODO add your handling code here:
        activaBotonGuardado();
    }//GEN-LAST:event_jTextField_nombreKeyReleased

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ActiveListWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ActiveListWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ActiveListWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ActiveListWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ActiveListWindow().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_AñadirActivo;
    private javax.swing.JButton jButton_RetirarActivo;
    private javax.swing.JButton jButton_cancelar;
    private javax.swing.JButton jButton_guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jList_ActivosAñadidos;
    private javax.swing.JList<String> jList_ActivosNoAñadidos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField_nombre;
    // End of variables declaration//GEN-END:variables

    /**
     * Metood que rellena las lista de la vista detalle de lista de activos
     */
    private void llenaListas() {
        //Obtengo los activos que ya están en la lista
        Set<Activo> activosActualesEnLaLista = this.listaDeActivos.getActivos();
        //Obtengo todos los activos en la BBDD
        List listaActivosTotales = BBDD.getSession().createQuery("from Activo").list();
        //Obtengo un iterador de la coleccion de activos en la lista
        Iterator<Activo> iteratorActivosActualesEnLaLista = activosActualesEnLaLista.iterator();
        //Añado nuevos modelos a las listas
        jList_ActivosAñadidos.setModel(new DefaultListModel<>());
        jList_ActivosNoAñadidos.setModel(new DefaultListModel<>());
        //Siempre que haya otro activo mas en la coleccion de activos de la lista...
        while (iteratorActivosActualesEnLaLista.hasNext()) {
            //Obtengo el siguiente activo en la coleccion
            Activo activo = iteratorActivosActualesEnLaLista.next();
            //Elimino ese activo de la coleccion de activos de la lista de la lista de activos totales
            listaActivosTotales.remove(activo);
            //Añado el activo eliminado de la lista de activos totales al modelo de la Jlist que representa la coleccion de activos en la lista de activos
            ((DefaultListModel) jList_ActivosAñadidos.getModel()).addElement(activo);
        }
        //Recorro la lista de activos totales... 
        for (Object activo : listaActivosTotales) {
            //Y lo añado al modelo de la Jlista que represeta los activos que no estan añadidos a la lista actual
            ((DefaultListModel) jList_ActivosNoAñadidos.getModel()).addElement(activo);
        }
    }

    private void mueveActivoDeLista(JList listaDeSustraccion, JList listaDeAdicion) {
        if (listaDeSustraccion.getModel().getSize() > 0) {
            if (listaDeSustraccion.getSelectedIndices().length == 0) {
                listaDeSustraccion.setSelectedIndex(0);
            }
            ((DefaultListModel) listaDeAdicion.getModel()).addElement(listaDeSustraccion.getSelectedValue());
            ((DefaultListModel) listaDeSustraccion.getModel()).remove(listaDeSustraccion.getSelectedIndex());
        }

    }

    private void guardarListaEnBBDD() {

        this.listaDeActivos.setNombre(jTextField_nombre.getText());
        this.listaDeActivos.setEsPrivada(true);
        ListModel<String> model = jList_ActivosAñadidos.getModel();
        Set<Activo> nuevoSet = new HashSet<Activo>();

        for (int i = 0; i < model.getSize(); i++) {
            Object o = model.getElementAt(i);
            nuevoSet.add((Activo) o);
        }
        this.listaDeActivos.setActivos(nuevoSet);
        Transaction tx = BBDD.getSession().beginTransaction();
        try {
            if (esCreacion) {
                BBDD.getSession().save(this.listaDeActivos);
            } else {
                BBDD.getSession().saveOrUpdate(this.listaDeActivos);
            }
            tx.commit();
            parentCrudWindow.loadContentOnTable();
            this.parentCrudWindow.parentMainWindow.refrescarComboDeListas();
            this.dispose();

        } catch (Exception e) {
            tx.rollback();
            BBDD.getSession().clear();
            JOptionPane.showMessageDialog(this, "Ya existe una lista con esos datos");
            java.util.logging.Logger.getLogger(AssetWindow.class.getName()).log(java.util.logging.Level.FINE, "Ya existe una lista con estos datos: " + this.listaDeActivos, "");
        }

    }

    private void activaBotonGuardado() {
        if (jTextField_nombre.getText().length() > 0) {
            jButton_guardar.setEnabled(true);
        } else {
            jButton_guardar.setEnabled(false);
        }
    }

}
