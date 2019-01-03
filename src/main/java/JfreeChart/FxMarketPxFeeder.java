/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JfreeChart;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.CandlestickRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.time.FixedMillisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.ohlc.OHLCSeries;
import org.jfree.data.time.ohlc.OHLCSeriesCollection;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author LacorZ
 */
public class FxMarketPxFeeder {
    private JfreeCandlestickChart jfreeCandlestickChart;
	private String stockTradesFile; 
	private int simulationTime;
	private ExecutorService executorService;
	
	public FxMarketPxFeeder(JfreeCandlestickChart jfreeCandlestickChart, String stockTradesFile, int simulationTime) {
		super();
		this.executorService = Executors.newCachedThreadPool();
		this.stockTradesFile = stockTradesFile;
		this.jfreeCandlestickChart = jfreeCandlestickChart;
		this.simulationTime = simulationTime;
	}

	public void run() {
		executorService.execute(() -> read());
	}

	private void read() {
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(this.getClass().getResourceAsStream(stockTradesFile)))) {
			while (true) {
				Thread.sleep(simulationTime);
				String line = br.readLine();
				if (line != null) {
					// Parse line and convert it to trade
					String[] tradeElements = line.split(Constants.DELIMITER);
					Trade t = new Trade(tradeElements[Constants.STOCK_IDX],
							TimeUtils.convertToMillisTime(tradeElements[Constants.TIME_IDX]),
							Double.parseDouble(tradeElements[Constants.PRICE_IDX]),
							Long.parseLong(tradeElements[Constants.SIZE_IDX]));
					// Add trade to the jfreeCandlestickChart 
					jfreeCandlestickChart.onTrade(t);
				} else {
					executorService.shutdown();
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
