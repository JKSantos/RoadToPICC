package com.gss.pdf.Reports;

import java.awt.Color;
import java.util.ArrayList;
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
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import com.gss.dao.Reports.ProductPurchasesRepository;
import com.gss.model.Reports.ProductDetail;
import com.gss.model.Reports.ProductPurchase;
import com.gss.model.Reports.ProductPurchaseDetail;
import com.gss.utilities.ReportsHelper;

public class ProductPurchasesStackedChart extends ApplicationFrame{

	private static final long serialVersionUID = 1L;
		private ProductPurchase purchase;
		private JFreeChart chart;
		private String title;

	    public ProductPurchasesStackedChart(String title, ProductPurchase purchase) {
	    	
	        super(title);
	        this.title = title;
	        this.purchase = purchase;
	        final CategoryDataset dataset = createDataset();
	        final JFreeChart chart = createChart(dataset);
	        this.chart = chart;
	        final ChartPanel chartPanel = new ChartPanel(chart);
	        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
	        setContentPane(chartPanel);

	    }
	    public JFreeChart getChart(){
	    	return this.chart;
	    }
	    
	    /**
	     * Creates a sample dataset.
	     * 
	     * @return a sample dataset.
	     */
	    private CategoryDataset createDataset() {
	    	 DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
	    	 
	    	 	List<String> series = new ArrayList<String>();
		    	
	    	 	for(int index = 0; index < this.purchase.getDetails().get(0).getProducts().size(); index++){
	    	 		ProductDetail detail = this.purchase.getDetails().get(0).getProducts().get(index);
	    	 		series.add(detail.getStrProductName());
	    	 	}
	    	 	
	    	 	
	    	 	for(int indexOuter = 0; indexOuter < purchase.getDetails().size(); indexOuter++){
	    	 		ProductPurchaseDetail purchaseDetail = purchase.getDetails().get(indexOuter);
	    	 		
	    	 		for(int indexInner = 0; indexInner < purchase.getDetails().get(indexOuter).getProducts().size(); indexInner++){
	    	 			ProductDetail details = purchase.getDetails().get(indexOuter).getProducts().get(indexInner);
	    	 			defaultcategorydataset.addValue(details.getTotalSales(), details.getStrProductName(), purchaseDetail.getClassification());
	    	 		}
	    	 		
	    	 	}
	    	 	

	    	    return defaultcategorydataset;
	    }
	    
	    /**
	     * Creates a sample chart.
	     * 
	     * @param dataset  the dataset for the chart.
	     * 
	     * @return a sample chart.
	     */
	    private JFreeChart createChart(final CategoryDataset dataset) {

	        final JFreeChart chart = ChartFactory.createStackedBarChart(
	            this.title.toUpperCase(),  // chart title
	            "Category",                  // domain axis label
	            "Value",                     // range axis label
	            dataset,                     // data
	            PlotOrientation.VERTICAL,    // the plot orientation
	            true,                        // legend
	            true,                        // tooltips
	            false                        // urls
	        );
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
	        
	        // set up gradient paints for series...
//	        final GradientPaint gp0 = new GradientPaint(
//	            0.0f, 0.0f, Color.blue, 
//	            0.0f, 0.0f, Color.lightGray
//	        );
//	        final GradientPaint gp1 = new GradientPaint(
//	            0.0f, 0.0f, Color.green, 
//	            0.0f, 0.0f, Color.lightGray
//	        );
//	        final GradientPaint gp2 = new GradientPaint(
//	            0.0f, 0.0f, Color.red, 
//	            0.0f, 0.0f, Color.lightGray
//	        );
	        renderer.setSeriesPaint(0, blue);
	        renderer.setSeriesPaint(1, black);
	        renderer.setSeriesPaint(2, green);
	        renderer.setSeriesPaint(2, orange);

	       
	        // OPTIONAL CUSTOMISATION COMPLETED.
	        
	        return chart;
	        
	    }



}
