/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.util.Calendar;
import java.util.Date;
import javafx.scene.layout.Border;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.data.xy.DefaultHighLowDataset;

/**
 *
 * @author LacorZ
 */
public class CandlestickChart extends JPanel {

    final private DefaultHighLowDataset dataset = null;
    final private JFreeChart chart;
    final private ChartPanel chartPanel;

    public CandlestickChart(Dimension dimension) {
        this(dimension, createDataset());
    }

    public CandlestickChart() {
        this(new Dimension(1280, 720), createDataset());
    }

    public CandlestickChart(DefaultHighLowDataset dataset) {
        this(new Dimension(1280, 720), dataset);
    }

    public CandlestickChart(Dimension dimension, DefaultHighLowDataset dataset) {
        chart = ChartFactory.createCandlestickChart("", "", "", dataset, false);
        chart.getXYPlot().setDomainPannable(true);
        chartPanel = new ChartPanel(chart);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setFillZoomRectangle(false);
        chartPanel.setPreferredSize(dimension);
        chartPanel.setVisible(true);      
        super.add(chartPanel, BorderLayout.CENTER);
    }

    private static DefaultHighLowDataset createDataset() {

        int serice = 150;

        Date[] date = new Date[serice];
        double[] high = new double[serice];
        double[] low = new double[serice];
        double[] open = new double[serice];
        double[] close = new double[serice];
        double[] volume = new double[serice];

        Calendar calendar = Calendar.getInstance();
        calendar.set(2008, 5, 1);

        date[0] = createData(2009, 8, 0 + 1);
        high[0] = 25;
        low[0] = 5;
        open[0] = 10;
        close[0] = 20;
        volume[0] = 0.0;

        for (int i = 1; i < serice; i++) {
            date[i] = createData(2008, 8, i + 1);
            high[i] = 30 + Math.round(10) + new Double(Math.random() * 20.0);
            low[i] = 30 + Math.round(10) + new Double(Math.random() * 20.0);
            open[i] = 10 + Math.round(10) + new Double(Math.random() * 20.0);
            close[i] = 10 + Math.round(10) + new Double(Math.random() * 20.0);
            volume[i] = 10.0 + new Double(Math.random() * 20.0);
        }

        DefaultHighLowDataset data = new DefaultHighLowDataset(
                "", date, high, low, open, close, volume);
        return data;
    }

    private static Date createData(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date);
        return calendar.getTime();
    }
    
    /**
     * Método que activa las AxisTrace del grafico
     * @param activar 
     */
    public void AxisTrace(boolean activar)
    {
        chartPanel.setVerticalAxisTrace(activar);
        chartPanel.setHorizontalAxisTrace(activar);
    }

//    public static void main(String args[]) {
//        CandlestickChart chart = new CandlestickChart("Candle Stick Chart");
//        chart.pack();
//        RefineryUtilities.centerFrameOnScreen(chart);
//        chart.setVisible(true);
//    }
}
