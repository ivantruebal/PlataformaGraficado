/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Interfaz;

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.data.xy.DefaultHighLowDataset;
import servicios.database.BBDD;
import static servicios.database.BBDD.getSession;
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
        cargarComboActivos();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField_caracterDeSeparacion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_Notas = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jTextField_rutaFicheroCSV = new javax.swing.JTextField();
        jButton_explorarArchivos = new javax.swing.JButton();
        jTextField_patronFecha = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton_abrirAyuda = new javax.swing.JButton();
        jComboBox_activos = new javax.swing.JComboBox<>();
        jButton_guardar = new javax.swing.JButton();
        jButton_Cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        jTextField_caracterDeSeparacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_caracterDeSeparacionKeyReleased(evt);
            }
        });

        jTextArea_Notas.setColumns(20);
        jTextArea_Notas.setRows(5);
        jScrollPane1.setViewportView(jTextArea_Notas);

        jLabel4.setText("Notas:");

        jTextField_rutaFicheroCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_rutaFicheroCSVActionPerformed(evt);
            }
        });
        jTextField_rutaFicheroCSV.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTextField_rutaFicheroCSVPropertyChange(evt);
            }
        });
        jTextField_rutaFicheroCSV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_rutaFicheroCSVKeyReleased(evt);
            }
        });

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

        jComboBox_activos.setEnabled(false);
        jComboBox_activos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_activosActionPerformed(evt);
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
                                .addComponent(jLabel2)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(80, 80, 80)
                                        .addComponent(jTextField_caracterDeSeparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox_activos, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(137, 137, 137)))
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
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox_activos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jButton_guardar.setText("Guardar");
        jButton_guardar.setEnabled(false);
        jButton_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_guardarActionPerformed(evt);
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
                .addComponent(jButton_guardar)
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
                    .addComponent(jButton_guardar)
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
        comprobarEstadoBotonGuardado();
    }//GEN-LAST:event_jButton_explorarArchivosActionPerformed

    private void jButton_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_guardarActionPerformed
        // TODO add your handling code here:
        guardarDatosDeFicheroEnBBDD();

    }//GEN-LAST:event_jButton_guardarActionPerformed

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

    private void jComboBox_activosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_activosActionPerformed
        // TODO add your handling code here:
        comprobarEstadoBotonGuardado();
    }//GEN-LAST:event_jComboBox_activosActionPerformed

    private void jTextField_rutaFicheroCSVPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTextField_rutaFicheroCSVPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField_rutaFicheroCSVPropertyChange

    private void jTextField_rutaFicheroCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_rutaFicheroCSVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_rutaFicheroCSVActionPerformed

    private void jTextField_rutaFicheroCSVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_rutaFicheroCSVKeyReleased
        // TODO add your handling code here:
        comprobarEstadoBotonGuardado();
    }//GEN-LAST:event_jTextField_rutaFicheroCSVKeyReleased

    private void jTextField_caracterDeSeparacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_caracterDeSeparacionKeyReleased
        // TODO add your handling code here:
        soloUnCaracter();
    }//GEN-LAST:event_jTextField_caracterDeSeparacionKeyReleased

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
//            java.util.logging.Logger.getLogger(CSVImportWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CSVImportWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CSVImportWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CSVImportWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CSVImportWindow().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JButton jButton_abrirAyuda;
    private javax.swing.JButton jButton_explorarArchivos;
    private javax.swing.JButton jButton_guardar;
    private javax.swing.JComboBox<Activo> jComboBox_activos;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea_Notas;
    private javax.swing.JTextField jTextField_caracterDeSeparacion;
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
            if (leerFicheroCSVmejorado()) {
                this.dispose();
                JOptionPane.showMessageDialog(new JFrame(), "Archivo importado correctamente", "",
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
        Activo activo = (Activo) jComboBox_activos.getSelectedItem();
        String caracterDeSeparacion = jTextField_caracterDeSeparacion.getText();
        String patronFecha = jTextField_patronFecha.getText();
        String rutaFichero = jTextField_rutaFicheroCSV.getText();
        Transaction transaction = BBDD.getSession().beginTransaction();
        File ficheroCSV = new File(rutaFichero);
        BufferedReader br = null;
        String line = "";
        int[] indiceColumnas = new int[6];
        indiceColumnas[0] = -1;
        indiceColumnas[1] = -1;
        indiceColumnas[2] = -1;
        indiceColumnas[3] = -1;
        indiceColumnas[4] = -1;
        indiceColumnas[5] = -1;
        boolean columnasDefinidas = false, cierreDefinido = false, fechaDefinida = false;

        if (activo != null) {
            try {
                br = new BufferedReader(new FileReader(ficheroCSV));
                while ((line = br.readLine()) != null) {
                    String[] data;
                    //Saltamos las lineas vacias
                    if (!line.equalsIgnoreCase("")) {

                        data = line.split(caracterDeSeparacion);
                        //Obtenemos el orden de las columnas is estas estan nombradas como espera el software
                        if (!columnasDefinidas) {
                            for (int indice = 0; indice < data.length; indice++) {
                                String columna = data[indice].trim();
                                if (columna.equalsIgnoreCase(BBDD.Indice.OPEN.name())) {
                                    indiceColumnas[BBDD.Indice.OPEN.ordinal()] = indice;
                                }
                                if (columna.equalsIgnoreCase(BBDD.Indice.HIGH.name())) {
                                    indiceColumnas[BBDD.Indice.HIGH.ordinal()] = indice;
                                }
                                if (columna.equalsIgnoreCase(BBDD.Indice.LOW.name())) {
                                    indiceColumnas[BBDD.Indice.LOW.ordinal()] = indice;
                                }
                                if (columna.equalsIgnoreCase(BBDD.Indice.CLOSE.name())) {
                                    indiceColumnas[BBDD.Indice.CLOSE.ordinal()] = indice;
                                    cierreDefinido = true;
                                }
                                if (columna.equalsIgnoreCase(BBDD.Indice.DATE.name())) {
                                    indiceColumnas[BBDD.Indice.DATE.ordinal()] = indice;
                                    fechaDefinida = true;
                                }
                                if (columna.equalsIgnoreCase(BBDD.Indice.VOLUME.name())) {
                                    indiceColumnas[BBDD.Indice.VOLUME.ordinal()] = indice;
                                }
                            }
                            //Si estan definidas las columnas minimas, se sigue con la lectura
                            if (cierreDefinido && fechaDefinida) {
                                columnasDefinidas = true;
                            } else {
                                throw new UnDefinedHeadersException("Las encabezados de las columnas del fichero CSV no estan bien definidas");
                            }

                        } else {

                            BigDecimal open;
                            if (indiceColumnas[BBDD.Indice.OPEN.ordinal()] != -1) {
                                open = new BigDecimal(data[indiceColumnas[BBDD.Indice.OPEN.ordinal()]]);
                            } else {
                                open = new BigDecimal(data[indiceColumnas[BBDD.Indice.CLOSE.ordinal()]]);
                            }
                            BigDecimal high;
                            if (indiceColumnas[BBDD.Indice.HIGH.ordinal()] != -1) {
                                high = new BigDecimal(data[indiceColumnas[BBDD.Indice.HIGH.ordinal()]]);
                            } else {
                                high = new BigDecimal(data[indiceColumnas[BBDD.Indice.CLOSE.ordinal()]]);
                            }
                            BigDecimal low;
                            if (indiceColumnas[BBDD.Indice.LOW.ordinal()] != -1) {
                                low = new BigDecimal(data[indiceColumnas[BBDD.Indice.LOW.ordinal()]]);
                            } else {
                                low = new BigDecimal(data[indiceColumnas[BBDD.Indice.CLOSE.ordinal()]]);
                            }
                            BigDecimal close;
                            close = new BigDecimal(data[indiceColumnas[BBDD.Indice.CLOSE.ordinal()]]);
                            BigDecimal volumen;
                            if (indiceColumnas[BBDD.Indice.VOLUME.ordinal()] != -1) {
                                volumen = new BigDecimal(data[indiceColumnas[BBDD.Indice.VOLUME.ordinal()]]);
                            } else {
                                volumen = new BigDecimal(0);
                            }

                            Calendar calendar = Calendar.getInstance();

                            SimpleDateFormat sdf = new SimpleDateFormat(patronFecha);
                            calendar.setTime(sdf.parse(data[indiceColumnas[BBDD.Indice.DATE.ordinal()]]));
                            Candlestick candlestick = new Candlestick(open, high, low, close, calendar, volumen, (Activo) activo);
                            BBDD.getSession().saveOrUpdate(candlestick);
                        }
                    }
                }
                transaction.commit();
                BBDD.getSession().clear();
                return true;
            } catch (FileNotFoundException ex) {
                java.util.logging.Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                transaction.rollback();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
                transaction.rollback();
            } finally {
//                session.close();
            }
        }

        java.util.logging.Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, "El activo es nulo");
        transaction.rollback();
        return false;
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

    private void cargarComboActivos() {
        Session session = BBDD.getSession();
        String nombre, simbolo, notas;
        notas = jTextArea_Notas.getText();
        Query createQuery = session.createQuery("from Activo");
        List<Activo> list = createQuery.list();
        for (Activo activo : list) {
            jComboBox_activos.addItem(activo);
        }
        if (list.size() > 0) {
            jComboBox_activos.setEnabled(true);
        }
    }

    private void comprobarEstadoBotonGuardado() {
        String rutaFichero = jTextField_rutaFicheroCSV.getText();
        final int longitudCadenaRuta = rutaFichero.length() - 4;
        if (longitudCadenaRuta > 0) {
            if (jComboBox_activos.getSelectedIndex() != -1 && rutaFichero.substring(longitudCadenaRuta).equalsIgnoreCase(".csv")) {
                jButton_guardar.setEnabled(true);
            } else {
                jButton_guardar.setEnabled(false);
            }
        }
    }

}
