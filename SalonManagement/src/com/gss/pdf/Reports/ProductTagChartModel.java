package com.gss.pdf.Reports;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import com.gss.model.TagSum;
import com.gss.model.Reports.TagReport;
import com.gss.model.Reports.TagReportDetail;

public class ProductTagChartModel extends ApplicationFrame{
	
	private JFreeChart chart;
	private TagReport report;
	private String title;

	
    public ProductTagChartModel(final String title, TagReport report) {
    	
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
        final String series[] = {"Defective", "Lost", "Expired", "Consumed"};

        // column keys...
        final String[] classification = new String[this.report.getDetails().size()];

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        
        for(int i = 0; i < report.getDetails().size(); i++){
        	TagReportDetail detail = report.getDetails().get(i);
        	classification[i] = detail.getClassification();
        }
        
        
        for(int i = 0; i < report.getDetails().size(); i++){
        	TagReportDetail detail = report.getDetails().get(i);
        	dataset.addValue(detail.getTotalDefective(), series[0], classification[i]);
        	dataset.addValue(detail.getTotalLost(), series[1], classification[i]);
        	dataset.addValue(detail.getTotalExpired(), series[2], classification[i]);
        	dataset.addValue(detail.getTotalConsumed(), series[3], classification[i]);
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
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        // disable bar outlines...
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        // set up gradient paints for series...
        final GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, Color.blue, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, Color.green, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp2 = new GradientPaint(
            0.0f, 0.0f, Color.red, 
            0.0f, 0.0f, Color.lightGray
        );
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
        
    }

}
