package com.gss.testers;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import javax.swing.JOptionPane;

import org.quartz.InterruptableJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;

import com.gss.model.Promo;

public class PromoCheckerJob implements InterruptableJob {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		try {
			System.out.println("Promo Checker Executing..");
			Promo.checkExpiredPromo();
		} catch (SQLException e) {
			System.out.print("Error occured while executing job....");
			e.printStackTrace();
		}
	}

	@Override
	public void interrupt() throws UnableToInterruptJobException {

		
	}
}
