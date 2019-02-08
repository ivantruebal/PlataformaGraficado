/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Interfaz;

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.transaction.Transactional;
import org.jfree.data.xy.DefaultHighLowDataset;
import servicios.database.BBDD;
import servicios.database.UnDefinedHeadersException;
import servicios.modelos.Activo;
import servicios.modelos.Candlestick;
import servicios.utils.Utils;

/**
 *
 * @author LacorZ
 */
public class CSVImportWindow extends javax.swing.JFrame {

    /**
     * Creates new form CSVImportWindow
     */
    public CSVImportWindow() {
        initComponents();
        Utils.generalSettings(this);

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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField_nombreActivo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField_caracterDeSeparacion = new javax.swing.JTextField();
        jTextField_Simbolo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_Notas = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jTextField_rutaFicheroCSV = new javax.swing.JTextField();
        jButton_explorarArchivos = new javax.swing.JButton();
        jTextField_patronFecha = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton_abrirAyuda = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton_Cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nombre del activo:");

        jLabel2.setText("Simbolo:");

        jLabel3.setText("Caracter de separacion:");

        jTextField_caracterDeSeparacion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField_caracterDeSeparacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_caracterDeSeparacion.setText(",");
        jTextField_caracterDeSeparacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_caracterDeSeparacionFocusLost(evt);
            }
        });

        jTextArea_Notas.setColumns(20);
        jTextArea_Notas.setRows(5);
        jScrollPane1.setViewportView(jTextArea_Notas);

        jLabel4.setText("Notas:");

        jButton_explorarArchivos.setText("Examinar");
        jButton_explorarArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_explorarArchivosActionPerformed(evt);
            }
        });

        jTextField_patronFecha.setText("yyyy-M-dd");

        jLabel5.setText("Ruta fichero CSV:");

        jLabel6.setText("Patron fecha datos:");

        jButton_abrirAyuda.setText("Ayuda");
        jButton_abrirAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_abrirAyudaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField_patronFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_abrirAyuda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel4)
                            .addComponent(jScrollPane1)
                            .addComponent(jLabel3)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField_caracterDeSeparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_nombreActivo, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextField_Simbolo)
                                        .addGap(83, 83, 83)))))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField_rutaFicheroCSV)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_explorarArchivos)
                        .addGap(16, 16, 16))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField_nombreActivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_Simbolo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField_caracterDeSeparacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_rutaFicheroCSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_explorarArchivos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_patronFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_abrirAyuda))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Cancelar)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton_Cancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_explorarArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_explorarArchivosActionPerformed
        // TODO add your handling code here:
        //String rutaFichero = examinarRutaFichero();
        //System.out.println(rutaFichero);
        examinarRutaFichero();
    }//GEN-LAST:event_jButton_explorarArchivosActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        guardarDatosDeFicheroEnBBDD();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField_caracterDeSeparacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_caracterDeSeparacionFocusLost
        // TODO add your handling code here:
        soloUnCaracter();
    }//GEN-LAST:event_jTextField_caracterDeSeparacionFocusLost

    private void jButton_abrirAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_abrirAyudaActionPerformed
        // TODO add your handling code here:
        abrirAyuda();
    }//GEN-LAST:event_jButton_abrirAyudaActionPerformed

    private void jButton_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton_CancelarActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CSVImportWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CSVImportWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CSVImportWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CSVImportWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CSVImportWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JButton jButton_abrirAyuda;
    private javax.swing.JButton jButton_explorarArchivos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea_Notas;
    private javax.swing.JTextField jTextField_Simbolo;
    private javax.swing.JTextField jTextField_caracterDeSeparacion;
    private javax.swing.JTextField jTextField_nombreActivo;
    private javax.swing.JTextField jTextField_patronFecha;
    private javax.swing.JTextField jTextField_rutaFicheroCSV;
    // End of variables declaration//GEN-END:variables

    private void examinarRutaFichero() {
        FileDialog fd = new FileDialog(new JFrame());
        fd.setVisible(true);
        File[] f = fd.getFiles();
        if (f.length > 0) {
            jTextField_rutaFicheroCSV.setText(fd.getFiles()[0].getAbsolutePath());
        } else {
            jTextField_rutaFicheroCSV.setText("");
        }
    }

    /**
     * Metodo que guarda los datos del fichero CSV en la tabla que contiene los
     * historicos de los activos.
     */
    
    private void guardarDatosDeFicheroEnBBDD() {
        try {
            if(leerFicheroCSVmejorado())
            {
                this.dispose();
                JOptionPane.showMessageDialog(new JFrame(),"Archivo importado correctamente", "",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (UnDefinedHeadersException ex) {
            Logger.getLogger(CSVImportWindow.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        } catch (ParseException ex) {
            Logger.getLogger(CSVImportWindow.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean leerFicheroCSVmejorado() throws UnDefinedHeadersException, ParseException {
        Activo activo = BBDD.compruebaSiExisteActivoYcrealo(jTextField_nombreActivo.getText(), jTextField_Simbolo.getText(), jTextArea_Notas.getText());
        if (activo != null) {
            return BBDD.generarColeccionDeCandlestickApartirDeFicheroCSV(jTextField_rutaFicheroCSV.getText(), jTextField_caracterDeSeparacion.getText().substring(0, 1), activo, jTextField_patronFecha.getText());
        } else {
            return false;
        }
    }

    /**
     * Método que lee el fichero CSV y genera un Set de Candlestick con los
     * datos leidos
     *
     * @return Set generado a partir de los datos lidos del fichero
     */
    private Set<Candlestick> leerFicheroCSV() {
        Set<Candlestick> candlestcksSet = null;
        if (jTextField_rutaFicheroCSV.getText().length() > 0) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(jTextField_rutaFicheroCSV.getText()));
                String line = br.readLine();
                while (null != line) {
                    String[] fields = line.split(jTextField_caracterDeSeparacion.getText());
                    Candlestick c = new Candlestick();
//                    c.setTimestamp(new Date(Long.parseLong(fields[0])));
                    c.setOpen(BigDecimal.valueOf(Double.parseDouble(fields[1])));
                    c.setHigh(BigDecimal.valueOf(Double.parseDouble(fields[2])));
                    c.setLow(BigDecimal.valueOf(Double.parseDouble(fields[3])));
                    c.setClose(BigDecimal.valueOf(Double.parseDouble(fields[4])));
                    candlestcksSet.add(c);
                    line = br.readLine();
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CSVImportWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CSVImportWindow.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (null != br) {
                    try {
                        br.close();
                    } catch (IOException ex) {
                        Logger.getLogger(CSVImportWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return candlestcksSet;
    }

    /**
     * Permite solo un caracter en el textField
     */
    private void soloUnCaracter() {
        if (jTextField_caracterDeSeparacion.getText().length() > 1) {
            jTextField_caracterDeSeparacion.setText(jTextField_caracterDeSeparacion.getText().charAt(0) + "");
        }
    }

    private double[] toDoubleArray(ArrayList<BigDecimal> high) {
        double douuble[] = new double[high.size()];

        for (int i = 0; i < high.size(); i++) {
            BigDecimal get = high.get(i);
            douuble[i] = get.doubleValue();
        }
        return douuble;
    }

    private void abrirAyuda() {
        new AyudaFormato().setVisible(rootPaneCheckingEnabled);
    }
}
