/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas.ivan;

import Presentacion.Interfaz.*;
import Presentacion.CandlestickChart;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.net.MalformedURLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartPanel;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author LacorZ
 */
public class MainWindow extends javax.swing.JFrame {

    private boolean isAlreadyOneClick;
    private boolean enableTrace;
    private CandlestickChartClass candlestickChart;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        //printChart();
        this.candlestickChart=new CandlestickChartClass(jPanel_Grafico.getSize());
        this.enableTrace=false;  
        metodo();
        enablePanelGraficoAutoSize();
        TrayIconDemo tid=new TrayIconDemo();
        tid.displayTray();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_General = new javax.swing.JPanel();
        jPanel_Activos = new javax.swing.JPanel();
        jComboBox_selectorListas = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_listaActivos = new javax.swing.JTable();
        jPanel_Graficos = new javax.swing.JPanel();
        jTabbedPane_Tools = new javax.swing.JTabbedPane();
        jTabbedPane_Graficos = new javax.swing.JTabbedPane();
        jPanel_Analisis = new javax.swing.JPanel();
        jPanel_HerramientasDeDibujado = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel_Grafico = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        jComboBox_selectorListas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Forex" }));

        jTable_listaActivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Ticker", "Cotizacion"
            }
        ));
        jTable_listaActivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_listaActivosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_listaActivos);

        javax.swing.GroupLayout jPanel_ActivosLayout = new javax.swing.GroupLayout(jPanel_Activos);
        jPanel_Activos.setLayout(jPanel_ActivosLayout);
        jPanel_ActivosLayout.setHorizontalGroup(
            jPanel_ActivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jComboBox_selectorListas, 0, 188, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel_ActivosLayout.setVerticalGroup(
            jPanel_ActivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ActivosLayout.createSequentialGroup()
                .addComponent(jComboBox_selectorListas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
        );

        jTabbedPane_Graficos.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        jTabbedPane_Graficos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane_GraficosFocusGained(evt);
            }
        });
        jTabbedPane_Graficos.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTabbedPane_GraficosComponentShown(evt);
            }
        });

        jLabel1.setText("Linea");

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_HerramientasDeDibujadoLayout = new javax.swing.GroupLayout(jPanel_HerramientasDeDibujado);
        jPanel_HerramientasDeDibujado.setLayout(jPanel_HerramientasDeDibujadoLayout);
        jPanel_HerramientasDeDibujadoLayout.setHorizontalGroup(
            jPanel_HerramientasDeDibujadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addGroup(jPanel_HerramientasDeDibujadoLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_HerramientasDeDibujadoLayout.setVerticalGroup(
            jPanel_HerramientasDeDibujadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_HerramientasDeDibujadoLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel_Grafico.setAutoscrolls(true);
        jPanel_Grafico.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel_Grafico.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel_AnalisisLayout = new javax.swing.GroupLayout(jPanel_Analisis);
        jPanel_Analisis.setLayout(jPanel_AnalisisLayout);
        jPanel_AnalisisLayout.setHorizontalGroup(
            jPanel_AnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_AnalisisLayout.createSequentialGroup()
                .addComponent(jPanel_HerramientasDeDibujado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Grafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_AnalisisLayout.setVerticalGroup(
            jPanel_AnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_HerramientasDeDibujado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_Grafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane_Graficos.addTab("EUR/USD", jPanel_Analisis);

        jTabbedPane_Tools.addTab("Graficos", jTabbedPane_Graficos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1081, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 671, Short.MAX_VALUE)
        );

        jTabbedPane_Tools.addTab("Noticias", jPanel2);

        javax.swing.GroupLayout jPanel_GraficosLayout = new javax.swing.GroupLayout(jPanel_Graficos);
        jPanel_Graficos.setLayout(jPanel_GraficosLayout);
        jPanel_GraficosLayout.setHorizontalGroup(
            jPanel_GraficosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane_Tools)
        );
        jPanel_GraficosLayout.setVerticalGroup(
            jPanel_GraficosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane_Tools)
        );

        jTabbedPane_Tools.getAccessibleContext().setAccessibleName("Grafico\n");

        javax.swing.GroupLayout jPanel_GeneralLayout = new javax.swing.GroupLayout(jPanel_General);
        jPanel_General.setLayout(jPanel_GeneralLayout);
        jPanel_GeneralLayout.setHorizontalGroup(
            jPanel_GeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_GeneralLayout.createSequentialGroup()
                .addComponent(jPanel_Activos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_Graficos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_GeneralLayout.setVerticalGroup(
            jPanel_GeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_Activos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel_Graficos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenu1.setText("Archivo");

        jMenu3.setText("Nuevo activo");
        jMenu1.add(jMenu3);

        jMenuItem1.setText("Importar datos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");

        jMenu4.setText("Acerca de...");
        jMenu2.add(jMenu4);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_General, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_General, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jTabbedPane_GraficosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane_GraficosFocusGained

    }//GEN-LAST:event_jTabbedPane_GraficosFocusGained

    private void jTabbedPane_GraficosComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTabbedPane_GraficosComponentShown

    }//GEN-LAST:event_jTabbedPane_GraficosComponentShown

    private void jTable_listaActivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_listaActivosMouseClicked
        // TODO add your handling code here:
        openChartOnDoubleClick();
    }//GEN-LAST:event_jTable_listaActivosMouseClicked

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        enableAxisTrance();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox_selectorListas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_Activos;
    private javax.swing.JPanel jPanel_Analisis;
    private javax.swing.JPanel jPanel_General;
    public javax.swing.JPanel jPanel_Grafico;
    private javax.swing.JPanel jPanel_Graficos;
    private javax.swing.JPanel jPanel_HerramientasDeDibujado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane_Graficos;
    private javax.swing.JTabbedPane jTabbedPane_Tools;
    private javax.swing.JTable jTable_listaActivos;
    // End of variables declaration//GEN-END:variables

    private void printChart() {
        
        CandlestickChart chart = new CandlestickChart(fixedDimensions(), null);
        System.out.println(jPanel_Grafico.getSize());
        jPanel_Grafico.setLayout(new BorderLayout());
        jPanel_Grafico.add(chart, BorderLayout.CENTER);        

    }

    private void metodo() {
        jPanel_Grafico.setLayout(new BorderLayout());
        jPanel_Grafico.add(candlestickChart, BorderLayout.CENTER);
    }
    private void enableAxisTrance(){
        if(enableTrace)
            enableTrace=false;
        else
            enableTrace=true;
        candlestickChart.AxisTrace(enableTrace);
        jPanel_Grafico.repaint();
    }
    /**
     * Añade un componentListener al jPanel_grafico para que se redimension cada vez que cambiamos el tamaño de la ventana
     */
    private void enablePanelGraficoAutoSize(){
        jPanel_Grafico.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent ce) {
                ChartPanel cp = (ChartPanel)candlestickChart.getComponent(0);
                cp.setPreferredSize(new java.awt.Dimension(candlestickChart.getWidth(), candlestickChart.getHeight()));
                cp.setSize(new java.awt.Dimension(candlestickChart.getWidth(), candlestickChart.getHeight()));
                // frame.invalidate();
                jPanel_Grafico.validate();
            }

            @Override
            public void componentMoved(ComponentEvent ce) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void componentShown(ComponentEvent ce) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void componentHidden(ComponentEvent ce) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    private void openChartOnDoubleClick() {
        if (isAlreadyOneClick) {            
            isAlreadyOneClick = false;
        } else {
            isAlreadyOneClick = true;
            //Añadir el CandlestickChart al JtabbedPane_graficos
            //jTabbedPane_Graficos.add(new)
            Timer t = new Timer("doubleclickTimer", false);
            t.schedule(new TimerTask() {

                @Override
                public void run() {
                    isAlreadyOneClick = false;
                }
            }, 300);
        }
    }

    private Dimension fixedDimensions() {
        Dimension parentSize = jPanel_Analisis.getSize();
        Dimension childSize = jPanel_HerramientasDeDibujado.getSize();
        return new Dimension(parentSize.width-childSize.width, parentSize.height);
    }
}
