/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Interfaz;

import Presentacion.CandlestickChart;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import org.jfree.chart.labels.CrosshairLabelGenerator;
import org.jfree.chart.panel.CrosshairOverlay;
import org.jfree.chart.plot.Crosshair;
import org.jfree.data.xy.DefaultHighLowDataset;
import servicios.GestorConexionAPI;
import servicios.modelos.Analisis;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Date;
import java.util.List;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.util.ShapeUtilities;
import servicios.database.BBDD;
import servicios.modelos.Forma2D;
import servicios.modelos.ListaDeActivos;
import servicios.modelos.Punto2D;

/**
 *
 * @author usuario
 */
public class PanelGrafico extends javax.swing.JPanel {

    private CandlestickChart candlestickChart;
    private GestorConexionAPI gcAPI;
    private DefaultHighLowDataset data;
    private boolean enableTrace;
    private String periodo;
    final CrosshairOverlay crosshairOverlay;
    final Crosshair yCrosshair;
    private Analisis analisis;
    private int contador;
    private ChartMouseListener mouseListenerCreaciónDeLinea;
    private Forma2D forma2D;

    /**
     * Crea un nuevo panel grafico
     *
     * @param analisis analisis que se mostrará en el panel grafico
     *
     */
    public PanelGrafico(Analisis analisis) {
        initComponents();
        enablePanelGraficoAutoSize();
        this.analisis = analisis;
        this.data = this.analisis.getActivo().getData();
        this.candlestickChart = new CandlestickChart(jPanel_Grafico.getSize(), this.data);
        this.gcAPI = new GestorConexionAPI();
        this.enableTrace = false;
        jPanel_Grafico.removeAll();
        jPanel_Grafico.setLayout(new BorderLayout());
        jPanel_Grafico.add(candlestickChart, BorderLayout.CENTER);
        jPanel_Grafico.repaint();
        this.crosshairOverlay = new CrosshairOverlay();
        this.yCrosshair = new Crosshair(Double.NaN, Color.DARK_GRAY, new BasicStroke(0f));
        this.yCrosshair.setLabelVisible(true);
        this.crosshairOverlay.addRangeCrosshair(yCrosshair);
        this.candlestickChart.getChartPanel().addOverlay(crosshairOverlay);
        this.contador = 0;
        seleccionPeriodo();

    }

    private double getLowestLow() {
        try {
            double lowest;
            lowest = data.getLowValue(0, 0);
            for (int i = 1; i < data.getItemCount(0); i++) {
                if (data.getLowValue(0, i) < lowest) {
                    lowest = data.getLowValue(0, i);
                }
            }
            return lowest;
        } catch (Exception e) {
            Logger.getLogger(PanelGrafico.class.getName()).log(Level.SEVERE, null, e);
            return 0.0;
        }
    }

    private double getHighestHigh() {
        double highest;
        highest = data.getHighValue(0, 0);
        for (int i = 1; i < data.getItemCount(0); i++) {
            if (data.getLowValue(0, i) > highest) {
                highest = data.getHighValue(0, i);
            }
        }

        return highest;
    }

    /**
     * Método que segun el valor indicado en el jComboBox_periodo le da un palor
     * al atributo periodo
     */
    private void seleccionPeriodo() {
        switch (jComboBox_periodo.getSelectedIndex()) {
            case 0:
                this.periodo = "1";
                break;
            case 1:
                this.periodo = "5";
                break;
            case 2:
                this.periodo = "15";
                break;
            case 3:
                this.periodo = "60";
                break;
            case 4:
                this.periodo = "240";
                break;
            case 5:
                this.periodo = "1440";
                break;
            case 6:
                this.periodo = "10080";
                break;
            case 7:
                this.periodo = "21600";
                break;
        }
    }

    /**
     * Método que devuelve el valor del periodo en formato String
     *
     * @return cadena de texto que indica los segundos del periodo
     */
    public String getPeriodo() {
        return this.periodo;
    }

    public CandlestickChart getCandlestickChart() {
        return candlestickChart;
    }

    /**
     * Método que devuelve el panel grafico del componente
     *
     * @return
     */
    public JPanel getjPanel_Grafico() {
        return jPanel_Grafico;
    }

    private void autoScale() {
        if (data != null && data.getItemCount(0) > 0) {
            double low = getLowestLow();
            double high = getHighestHigh();
            double range = high - low;
            range *= 0.1;
            candlestickChart.getChart().getXYPlot().getRangeAxis().setRange(low - range, high + range);
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

        jPanel_AreaDeAnalisis = new javax.swing.JPanel();
        jPanel_HerramientasDeDibujado = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel_Grafico = new javax.swing.JPanel();
        jComboBox_periodo = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();

        jLabel1.setText("Linea");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel1MouseReleased(evt);
            }
        });

        jLabel2.setText("jLabel2");

        jLabel3.setText("jLabel3");

        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout jPanel_HerramientasDeDibujadoLayout = new javax.swing.GroupLayout(jPanel_HerramientasDeDibujado);
        jPanel_HerramientasDeDibujado.setLayout(jPanel_HerramientasDeDibujadoLayout);
        jPanel_HerramientasDeDibujadoLayout.setHorizontalGroup(
            jPanel_HerramientasDeDibujadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_HerramientasDeDibujadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_HerramientasDeDibujadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_HerramientasDeDibujadoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_HerramientasDeDibujadoLayout.createSequentialGroup()
                        .addGroup(jPanel_HerramientasDeDibujadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel_HerramientasDeDibujadoLayout.setVerticalGroup(
            jPanel_HerramientasDeDibujadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_HerramientasDeDibujadoLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addContainerGap(361, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_GraficoLayout = new javax.swing.GroupLayout(jPanel_Grafico);
        jPanel_Grafico.setLayout(jPanel_GraficoLayout);
        jPanel_GraficoLayout.setHorizontalGroup(
            jPanel_GraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel_GraficoLayout.setVerticalGroup(
            jPanel_GraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jComboBox_periodo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1m", "5m", "15m", "1h", "4h", "1D", "7D", "15D" }));
        jComboBox_periodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_periodoActionPerformed(evt);
            }
        });

        jButton1.setText("Venta");

        jButton2.setText("Compra");

        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("0");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel_AreaDeAnalisisLayout = new javax.swing.GroupLayout(jPanel_AreaDeAnalisis);
        jPanel_AreaDeAnalisis.setLayout(jPanel_AreaDeAnalisisLayout);
        jPanel_AreaDeAnalisisLayout.setHorizontalGroup(
            jPanel_AreaDeAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_AreaDeAnalisisLayout.createSequentialGroup()
                .addComponent(jPanel_HerramientasDeDibujado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_AreaDeAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_AreaDeAnalisisLayout.createSequentialGroup()
                        .addComponent(jComboBox_periodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 539, Short.MAX_VALUE))
                    .addComponent(jPanel_Grafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel_AreaDeAnalisisLayout.setVerticalGroup(
            jPanel_AreaDeAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_AreaDeAnalisisLayout.createSequentialGroup()
                .addGroup(jPanel_AreaDeAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_AreaDeAnalisisLayout.createSequentialGroup()
                        .addGroup(jPanel_AreaDeAnalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox_periodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel_Grafico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel_AreaDeAnalisisLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel_HerramientasDeDibujado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1064, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_AreaDeAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 506, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_AreaDeAnalisis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox_periodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_periodoActionPerformed
        seleccionPeriodo();
    }//GEN-LAST:event_jComboBox_periodoActionPerformed

    private void jLabel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseReleased
        añadirListenerParaCrearForma2dAlGrafico("Linea");
    }//GEN-LAST:event_jLabel1MouseReleased

    /**
     * Metodo que se encarga de activar el dibujado y guardarlos en BBDD
     *
     * @param tipoForma
     */
    private void añadirListenerParaCrearForma2dAlGrafico(String tipoForma) {
        final ChartPanel chartPanel = candlestickChart.getChartPanel();

        this.mouseListenerCreaciónDeLinea = new ChartMouseListener() {

            @Override
            public void chartMouseClicked(ChartMouseEvent cme) {
                int numPuntos = 0;
                switch (tipoForma) {
                    case "Linea":
                        numPuntos = 2;
                        break;
                    case "Rectangulo":
                        numPuntos = 2;
                        break;
                }
                try {
                    if (contador < numPuntos) {
                        if (contador == 0) {
                            if (!BBDD.getSession().getTransaction().isActive()) {
                                BBDD.getSession().beginTransaction();
                            } else {
                                BBDD.getSession().getTransaction().rollback();
                            }
                            forma2D = new Forma2D(analisis, tipoForma);
                            BBDD.getSession().save(forma2D);
                        }

                        Punto2D crearPunto2d = crearPunto2d(cme, forma2D);
                        BBDD.getSession().save(crearPunto2d);
                        if (contador == numPuntos - 1) {
                            contador = 0;
//                        mostrarForma(forma2D);
                            forma2D = null;
                            BBDD.getSession().getTransaction().commit();
                            BBDD.getSession().clear();
                            desactivarListenersDelRatonDelGrafico();
                        }
                        contador++;
                    }
                } catch (Exception e) {
                    Logger.getLogger(PanelGrafico.class.getName()).log(Level.SEVERE, null, e);
                    if (BBDD.getSession().getTransaction().isActive()) {
                        BBDD.getSession().getTransaction().rollback();
                    }
                }
            }

            private Punto2D crearPunto2d(ChartMouseEvent cme, Forma2D forma2D1) {
                Point2D po = chartPanel.translateScreenToJava2D(cme.getTrigger().getPoint());
                Rectangle2D plotArea = chartPanel.getScreenDataArea();
                XYPlot plot = (XYPlot) chartPanel.getChart().getPlot(); // your plot
                double chartX = plot.getDomainAxis().java2DToValue(po.getX(), plotArea, plot.getDomainAxisEdge());
                double chartY = plot.getRangeAxis().java2DToValue(po.getY(), plotArea, plot.getRangeAxisEdge());
                return new Punto2D(chartX, chartY, forma2D1);

            }

            @Override
            public void chartMouseMoved(ChartMouseEvent cme) {

            }
            // TODO add your handling code here:

        };
        chartPanel.addChartMouseListener(this.mouseListenerCreaciónDeLinea);
        System.out.println("Activada la creacion de linea");
    }

   

    

    

    

    private void desactivarListenersDelRatonDelGrafico() {
        candlestickChart.getChartPanel().removeChartMouseListener(this.mouseListenerCreaciónDeLinea);
        System.out.println("Desactivada la creacion de linea");
        System.out.println("Creada linea");

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox_periodo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel_AreaDeAnalisis;
    private javax.swing.JPanel jPanel_Grafico;
    private javax.swing.JPanel jPanel_HerramientasDeDibujado;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    /**
     * Añade un componentListener al jPanel_grafico para que se redimension cada
     * vez que cambiamos el tamaño de la ventana
     */
    private void enablePanelGraficoAutoSize() {
        jPanel_Grafico.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent ce) {
                ChartPanel cp = (ChartPanel) candlestickChart.getComponent(0);
                cp.setPreferredSize(new java.awt.Dimension(candlestickChart.getWidth(), candlestickChart.getHeight()));
                cp.setSize(new java.awt.Dimension(candlestickChart.getWidth(), candlestickChart.getHeight()));
                // frame.invalidate();
                jPanel_Grafico.validate();
            }

            @Override
            public void componentMoved(ComponentEvent ce) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void componentShown(ComponentEvent ce) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void componentHidden(ComponentEvent ce) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    /**
     * Pinta un grafico he inicia un hilo en el que va tomando datos
     *
     * @param lista
     */
    public void pintarGrafico() {
        if (data != null) {
            candlestickChart.getChartPanel().getChart().getXYPlot().setDataset(data);
            crosshair();
            jPanel_Grafico.removeAll();
            jPanel_Grafico.setLayout(new BorderLayout());
            jPanel_Grafico.add(candlestickChart, BorderLayout.CENTER);
            jPanel_Grafico.repaint();
            autoScale();
        }

    }

    /**
     * Método que se encarga de modificar el ultimo dato, si la api devulve un
     * nuevo valor, este es agregado.
     *
     */
    public void pintarUltimoDato() {
        DefaultHighLowDataset d = data;
//        data = gcAPI.getUltimoDato(d, analisis.getActivo().getSimbolo(), periodo);
        candlestickChart.getChartPanel().getChart().getXYPlot().setDataset(data);
        crosshair();
        jPanel_Grafico.removeAll();
        jPanel_Grafico.setLayout(new BorderLayout());
        jPanel_Grafico.add(candlestickChart, BorderLayout.CENTER);
        jPanel_Grafico.repaint();
        //autoScale();
    }

    private Date getFechaMayor() {
        Date fechaMayor=new Date();
        if(data.getItemCount(0)>0){
            fechaMayor = data.getXDate(0, 0);
            for (int i = 1; i < data.getItemCount(0); i++) {
                if (data.getXDate(0, i).getTime() > fechaMayor.getTime()) {
                    fechaMayor = data.getXDate(0, i);
                }
            }
        }
        return fechaMayor;
    }
    /**
     * Método que pinta una linea en el eje y que que coincide con el valor
     * close del ultimo candlestick.
     */
    private void crosshair() {
        /*final int index = data.getItemCount(0) - 1;
        if (index > 0) {
            yCrosshair.setValue(data.getCloseValue(0, index));
            yCrosshair.setLabelGenerator(new CrosshairLabelGenerator() {
                @Override
                public String generateLabel(Crosshair crshr) {
                    return data.getCloseValue(0, data.getItemCount(0) - 1) + "";
                }
            });
        }*/
        Date fecha=getFechaMayor();
        for (int i = 1; i < data.getItemCount(0); i++) {
            if (data.getXDate(0, i).getTime() == fecha.getTime()) {
                yCrosshair.setValue(data.getCloseValue(0, i));
                yCrosshair.setLabelGenerator(new CrosshairLabelGenerator() {
                    @Override
                    public String generateLabel(Crosshair crshr) {
                        return data.getCloseValue(0, data.getItemCount(0) - 1) + "";
                    }
                });
            }
        }
        
    }
    //Comentario de prueba

    /**
     * Método que activa las lineas de trazao en el gráfico.
     */
    private void enableAxisTrance() {
        if (enableTrace) {
            enableTrace = false;
        } else {
            enableTrace = true;
        }
        candlestickChart.AxisTrace(enableTrace);
        jPanel_Grafico.repaint();
    }

    public Analisis getAnalisis() {
        return analisis;
    }

}
