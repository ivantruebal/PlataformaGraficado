/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas.ramon.sinPaquete;

/**
 *
 * @author LacorZ
 */
import java.awt.Color;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.FixedMillisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;

public class NewClass extends JFrame {

    public NewClass(final String title) {
        super(title);
        setContentPane(chartPanel);
        setSize(800, 600);
        setVisible(true);
    }

    TimeSeries s1 = new TimeSeries("s1");
    TimeSeries s2 = new TimeSeries("s2");

    int sc = 0;

    public void doEvent_avg(double[] avg) {
        sc++;

        s1.addOrUpdate(new FixedMillisecond(new Date().getTime()),
                       new Float(avg[0]));

        if (sc > 100) {
            s1.delete(0, 0);
        }
    }

    public void doEvent_outY(double[] outX) {
        sc++;

        s2.addOrUpdate(new FixedMillisecond(new Date().getTime() + 5000),
                       new Float(outX[0]));

        if (sc > 100) {
            s2.delete(0, 0);
        }
    }

    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart =
            ChartFactory.createTimeSeriesChart("EUR/USD - 3", // title
                "Time", // x-axis label
                "inX[0]", // y-axis label
                dataset, // data
                true, // create legend?
                true, // generate tooltips?
                false) // generate URLs?
        ;
        XYPlot plot = (XYPlot)chart.getPlot();
        XYDataset dataset2 = createDataset2();
        plot.setDataset(0, dataset);
        plot.setDataset(1, dataset2);
        DateAxis axis = (DateAxis)plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("hh:mm:ss"));
        XYItemRenderer r = plot.getRenderer();
        XYItemRenderer renderer2 = new XYLineAndShapeRenderer();
        r.setSeriesPaint(0, Color.blue);
        r.setSeriesPaint(1, Color.red);
        renderer2.setSeriesPaint(0, Color.red);
        plot.setRenderer(1, renderer2);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(1.0, 1.0, 1.0, 1.0));
        NumberAxis axis2 = new NumberAxis("outX[0]");
        axis2.setAutoRangeIncludesZero(false);
        axis2.setLabelPaint(Color.red);
        axis2.setTickLabelPaint(Color.green);
        plot.setRangeAxis(1, axis2);
        plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);
        plot.mapDatasetToRangeAxis(1, 1);

        return chart;
    }

    private XYDataset createDataset() {

        TimeSeriesCollection dataset = new TimeSeriesCollection();

        dataset.addSeries(s1);

        return dataset;
    }

    private XYDataset createDataset2() {

        TimeSeriesCollection dataset2 = new TimeSeriesCollection();

        dataset2.addSeries(s2);

        return dataset2;
    }

    public JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        ChartPanel panel = new ChartPanel(chart);
        panel.setFillZoomRectangle(true);
        panel.setMouseWheelEnabled(true);
     
        return panel;
    }
    ChartPanel chartPanel = (ChartPanel)createDemoPanel();
}
