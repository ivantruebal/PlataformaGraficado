/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Interfaz;

import java.awt.Container;
import javax.swing.JOptionPane;
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
public class AssetWindow extends javax.swing.JFrame {

    /**
     * Creates new form VentanaActivo
     */
    private Activo activo = null;
    private boolean esCreacion;
    private final CRUDWindow parentCRUDWindow;

    public AssetWindow(Activo entidad, CRUDWindow cRUDWindow) {
        initComponents();
        Utils.generalSettings(this);
        estableceEntidad(entidad);
        this.parentCRUDWindow = cRUDWindow;
        if(activo != null)
        {
            jTextField_nombreActivo.setText(activo.getNombre());
            jTextField_Simbolo.setText(activo.getSimbolo());
            jTextArea_Notas.setText(activo.getNotas());
        }
        
    }
    
    private void estableceEntidad(Activo entidad) {
        if (entidad == null) {
            this.activo = new Activo();
            esCreacion = true;
        } else {
            this.activo = entidad;
            esCreacion = false;
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

        jLabel1 = new javax.swing.JLabel();
        jTextField_nombreActivo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField_Simbolo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_Notas = new javax.swing.JTextArea();
        jButton_Guardar = new javax.swing.JButton();
        jButton_Cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nombre del activo:");

        jTextField_nombreActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nombreActivoActionPerformed(evt);
            }
        });
        jTextField_nombreActivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_nombreActivoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_nombreActivoKeyReleased(evt);
            }
        });

        jLabel2.setText("Simbolo:");

        jTextField_Simbolo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_SimboloKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_SimboloKeyReleased(evt);
            }
        });

        jLabel4.setText("Notas:");

        jTextArea_Notas.setColumns(20);
        jTextArea_Notas.setRows(5);
        jScrollPane1.setViewportView(jTextArea_Notas);

        jButton_Guardar.setText("Guardar");
        jButton_Guardar.setEnabled(false);
        jButton_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GuardarActionPerformed(evt);
            }
        });

        jButton_Cancelar.setText("Cancelar");
        jButton_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_Simbolo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_nombreActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton_Guardar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_Cancelar))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField_nombreActivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_Simbolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Guardar)
                    .addComponent(jButton_Cancelar))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GuardarActionPerformed
        // TODO add your handling code here:
        guardarActivoEnBBDD();

    }//GEN-LAST:event_jButton_GuardarActionPerformed

    private void jButton_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton_CancelarActionPerformed

    private void jTextField_nombreActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nombreActivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nombreActivoActionPerformed

    private void jTextField_nombreActivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_nombreActivoKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField_nombreActivoKeyPressed

    private void jTextField_SimboloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_SimboloKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField_SimboloKeyPressed

    private void jTextField_nombreActivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_nombreActivoKeyReleased
        // TODO add your handling code here:
        comprobarYactivarBotonGuardar();
    }//GEN-LAST:event_jTextField_nombreActivoKeyReleased

    private void jTextField_SimboloKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_SimboloKeyReleased
        // TODO add your handling code here:
        comprobarYactivarBotonGuardar();
    }//GEN-LAST:event_jTextField_SimboloKeyReleased

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
//            java.util.logging.Logger.getLogger(AssetWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AssetWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AssetWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AssetWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AssetWindow().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JButton jButton_Guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea_Notas;
    private javax.swing.JTextField jTextField_Simbolo;
    private javax.swing.JTextField jTextField_nombreActivo;
    // End of variables declaration//GEN-END:variables

    private void guardarActivoEnBBDD() {

        this.activo.setNombre(jTextField_nombreActivo.getText());
        this.activo.setSimbolo(jTextField_Simbolo.getText());
        this.activo.setNotas(jTextArea_Notas.getText());
        Transaction tx = BBDD.getSession().beginTransaction();
        try {
            if (esCreacion) {
                BBDD.getSession().save(this.activo);
            } else {
                BBDD.getSession().saveOrUpdate(this.activo);
            }
            tx.commit();
            parentCRUDWindow.loadContentOnTable();
            this.dispose();
            
        } catch (Exception e) {
            tx.rollback();       
            BBDD.getSession().clear();
            JOptionPane.showMessageDialog(this, "Ya existe un activo con esos datos");
            java.util.logging.Logger.getLogger(AssetWindow.class.getName()).log(java.util.logging.Level.FINE, "Ya existe un activo con estos datos: " + this.activo.toStringFull(), "");
        }

    }

    private void comprobarYactivarBotonGuardar() {
        if (jTextField_nombreActivo.getText().length() > 0 && jTextField_Simbolo.getText().length() > 0) {
            jButton_Guardar.setEnabled(true);
        } else {
            jButton_Guardar.setEnabled(false);
        }
    }
}
