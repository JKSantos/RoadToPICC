package com.gss.testers;

import java.util.ArrayList;
import java.util.List;

import com.gss.model.Job;
import com.gss.utilities.JobQualificationHelper;

public class JobQualificationTester {
	
	public static void main(String[] args){
		
		JobQualificationHelper help = new JobQualificationHelper();
		
		Job job = new Job("ADMIN", 1);
		Job job2 = new Job("CASHIER", 0);
		Job job3 = new Job("STYLIST", 1);
		List<Job> newList = new ArrayList<Job>();
		
		Job job4 = new Job("ADMIN", 1);
		Job job5 = new Job("CASHIER", 1);
		List<Job> oldList = new ArrayList<Job>();
		
		newList.add(job);
		newList.add(job2);
		newList.add(job3);
		oldList.add(job4);
		oldList.add(job5);
		
		List<String[]> finalList = help.checkChanges(newList, oldList);
		
		for(int i = 0; i < finalList.size(); i++)
		{
			String[] finalL = finalList.get(i);
			System.out.print(finalL[0] + " " + finalL[1] + " " + finalL[2] + "\n");
		}
	}

}
