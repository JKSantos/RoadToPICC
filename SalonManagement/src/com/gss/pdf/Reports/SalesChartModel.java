package com.gss.pdf.Reports;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import com.gss.model.Reports.SalesReport;
import com.gss.model.Reports.SalesReportDetail;
import com.gss.model.Reports.TagReport;
import com.gss.model.Reports.TagReportDetail;

public class SalesChartModel extends ApplicationFrame{
	
	private static final long serialVersionUID = 1L;
	private JFreeChart chart;
	private SalesReport report;
	private String title;

	
    public SalesChartModel(final String title, SalesReport report) {
    	
    	super(title);
        this.title = title;
        this.report = report;
        final CategoryDataset dataset = createDataset();
        this.chart = createChart(dataset);
    }
    
    public JFreeChart getChart(){
    	return this.chart;
    }

    /**
     * Returns a sample dataset.
     * 
     * @return The dataset.
     */
    private CategoryDataset createDataset() {
        
        // row keys...
        final String series[] = {"Home Service", "Event", "Walk In", "Delivery", "Pick Up"};

        // column keys...
        final String[] classification = new String[this.report.getDetails().size()];

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        
        for(int i = 0; i < report.getDetails().size(); i++){
        	SalesReportDetail detail = report.getDetails().get(i);
        	classification[i] = detail.getClassification();
        }
        
        
        for(int i = 0; i < report.getDetails().size(); i++){
        	SalesReportDetail detail = report.getDetails().get(i);
        	dataset.addValue(detail.getHomeService(), series[0], classification[i]);
        	dataset.addValue(detail.getEventService(), series[1], classification[i]);
        	dataset.addValue(detail.getWalkin(), series[2], classification[i]);
        	dataset.addValue(detail.getDelivery(), series[3], classification[i]);
        	dataset.addValue(detail.getPickup(), series[4], classification[i]);
        }
 
        return dataset;
        
    }
    
    /**
     * Creates a sample chart.
     * 
     * @param dataset  the dataset.
     * 
     * @return The chart.
     */
    private JFreeChart createChart(final CategoryDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
            title.toUpperCase(),         // chart title
            "Category",               // domain axis label
            "Value",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );
        
        

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.black);
        plot.setRangeGridlinePaint(Color.black);
        
        ((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
        
        Color blue = new Color(124, 181, 236);
        Color black = new Color(67, 67, 72);
        Color green = new Color(144, 237, 125);
        Color orange = new Color(247, 163, 92);
        
        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        renderer.setSeriesPaint(0, blue);
        renderer.setSeriesPaint(1, black);
        renderer.setSeriesPaint(2, green);
        renderer.setSeriesPaint(2, orange);

        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
        
    }

}
