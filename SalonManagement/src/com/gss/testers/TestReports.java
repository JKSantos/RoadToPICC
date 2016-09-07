package com.gss.testers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.jfree.ui.RefineryUtilities;

import com.gss.actions.Reports.ProductTagReportFactory;
import com.gss.model.ProductTagReport;
import com.gss.model.TagSum;
import com.gss.utilities.BarChart;
import com.gss.utilities.PieGraph;
import com.gss.utilities.Receipt;
import com.itextpdf.text.DocumentException;

public class TestReports {

	
	public static void main(String[] args){
		testproductTags();
	}
	
	public static void testproductTags(){
		ProductTagReportFactory factory = new ProductTagReportFactory();
		
		
		List<TagSum> sum = TagSum.getTagSum("2016-08-01", "2016-08-25");
		
		JFreeChart chart = PieGraph.getProductTags(sum, "2016-8-1", "2016-8-25");
		
		final BarChart demo = new BarChart("Bar Chart Demo", sum, "2016-08-01", "2016-08-25");
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
//        demo.setVisible(true);
			
		
		try {
			String str = factory.generateReport("2016-8-1","2016-8-25", ProductTagReport.getProductTagReport("2016-8-1", "2016-8-25"), sum, demo.getChart());
			System.out.print(str);
		
		} catch (DocumentException | IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
