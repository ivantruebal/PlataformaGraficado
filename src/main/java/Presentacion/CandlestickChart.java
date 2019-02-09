/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;
import java.util.Calendar;
import java.util.Date;
import javafx.scene.layout.Border;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.event.AxisChangeEvent;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.event.PlotChangeListener;

import org.jfree.data.xy.DefaultHighLowDataset;

/**
 *
 * @author LacorZ
 */
public class CandlestickChart extends JPanel {

    private DefaultHighLowDataset data = null;
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
        this.data = dataset;
        chart = ChartFactory.createCandlestickChart("", "", "", dataset, false);
        chart.getXYPlot().setDomainPannable(false);
        chartPanel = new ChartPanel(chart);
        autorange();
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setMouseZoomable(true);
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

        for (int i = 0; i < serice; i++) {
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
     *
     * @param activar
     */
    public void AxisTrace(boolean activar) {
        chartPanel.setVerticalAxisTrace(activar);
        chartPanel.setHorizontalAxisTrace(activar);
    }

    public ChartPanel getChartPanel() {
        return this.chartPanel;
    }

    public JFreeChart getChart() {
        return this.chart;
    }

    private void autorange() {
        chartPanel.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent mwe) {
                autoScale();
            }
        });
    }

    private double getLowestLow() {
        data = (DefaultHighLowDataset) chartPanel.getChart().getXYPlot().getDataset();
        double lowest;
        lowest = data.getLowValue(0, 0);
        for (int i = 1; i < data.getItemCount(0); i++) {
            if (data.getLowValue(0, i) < lowest) {
                lowest = data.getLowValue(0, i);
            }
        }
        return lowest;
    }

    private double getHighestHigh() {
        data = (DefaultHighLowDataset) chartPanel.getChart().getXYPlot().getDataset();
        double highest;
        highest = data.getHighValue(0, 0);
        for (int i = 1; i < data.getItemCount(0); i++) {
            if (data.getLowValue(0, i) > highest) {
                highest = data.getHighValue(0, i);
            }
        }
        return highest;
    }

    private void autoScale() {
        double low = getLowestLow();
        double high = getHighestHigh();
        double range = high - low;
        range *= 0.1;
        chartPanel.getChart().getXYPlot().getRangeAxis().setRange(low - range, high + range);
    }
}
