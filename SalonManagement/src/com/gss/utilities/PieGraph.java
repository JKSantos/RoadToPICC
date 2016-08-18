package com.gss.utilities;

import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import com.gss.model.ProductTagReport;
import com.gss.model.TagSum;

public class PieGraph {
	
	public static JFreeChart getProductTags(List<TagSum> list, String dateFrom, String dateTo) {
		DefaultPieDataset dataSet = new DefaultPieDataset();
		
		
		for(int i = 0; i < list.size(); i++){
			
			TagSum tag = list.get(i);
			
			dataSet.setValue(tag.getStrProductName() + "("+ String.valueOf(tag.getIntQuantity()) +" pcs.)", tag.getIntQuantity());
		}
		
		JFreeChart chart = ChartFactory.createPieChart(
				"Total Tags Made From " + dateFrom +" to "+ dateTo, dataSet, true, true, false);

		return chart;
	}

}
