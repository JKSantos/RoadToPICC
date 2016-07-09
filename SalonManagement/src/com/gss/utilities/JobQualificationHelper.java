package com.gss.utilities;

import java.util.ArrayList;
import java.util.List;

import com.gss.model.Job;

public class JobQualificationHelper {
	
	public List<String[]> checkChanges(List<Job> newList, List<Job> oldList){
		
		List<String[]> finalList = new ArrayList<String[]>();
		
		System.out.println("Added jobs: " + newList.size());
		System.out.println("Old jobs: " + oldList.size());
		
		for(int intOuter = 0; intOuter < newList.size(); intOuter++){
			
			Job newJob = newList.get(intOuter);
			
			if(oldList.size() > 0){
				for(int intInner = 0; intInner < oldList.size(); intInner++){
					if(newJob.getStrJobDesc().equals(oldList.get(intInner).getStrJobDesc()) && newJob.getIntJobStatus() == oldList.get(intInner).getIntJobStatus()){
				
						String[] finalJob = {"same", newJob.getStrJobDesc(), String.valueOf(newJob.getIntJobStatus())};
						finalList.add(finalJob);
						break;
					
					}
					else if(newJob.getStrJobDesc().equals(oldList.get(intInner).getStrJobDesc()) && newJob.getIntJobStatus() != oldList.get(intInner).getIntJobStatus()){
						
						String[] finalJob = {"update", newJob.getStrJobDesc(), String.valueOf(newJob.getIntJobStatus())};
						finalList.add(finalJob);
						break;
						
					}
					else if(intInner == (oldList.size() - 1)){
						String[] finalJob = {"new", newJob.getStrJobDesc(), String.valueOf(newJob.getIntJobStatus())};
						finalList.add(finalJob);
					}
				}
			}
			else{
				for(int intInner = 0; intInner < newList.size(); intInner++){
					Job newJob2 = newList.get(intInner);
					String[] finalJob = {"new", newJob2.getStrJobDesc(), String.valueOf(newJob2.getIntJobStatus())};
					finalList.add(finalJob);
					
				}
				
				return finalList;
			}
		}

		return finalList;
		
	}
	
	public List<Job> getUpdatedJob(List<Job> newJobs, List<Job> oldJobs){
		
		List<Job> updatedList = new ArrayList<Job>();
		List<Job> oldActiveJob = new ArrayList<Job>();
		Job newJob;
		Job oldJob;
		
		for(int intOuter = 0; intOuter < oldJobs.size(); intOuter++){
			
			oldJob = oldJobs.get(intOuter);
			
			if(oldJob.getIntJobStatus() == 1){	
				for(int intInner = 0; intInner < newJobs.size(); intInner++){
				
					newJob = newJobs.get(intInner);
				
					if(newJob.getStrJobDesc().equals(oldJob.getStrJobDesc()) && newJob.getIntJobStatus() == oldJob.getIntJobStatus()){
						//do nothing
					}
				
					else if(intInner == newJobs.size()){
					
					oldJob.setIntJobStatus(0);
					updatedList.add(oldJob);
					}
				}
			}
		}
		
		for(int intOuter = 0; intOuter < newJobs.size(); intOuter++){
			
			newJob = newJobs.get(intOuter);
			
			for(int intInner = 0; intInner < oldJobs.size(); intInner++){
				
				oldJob = oldJobs.get(intInner);
				
				if(newJob.getStrJobDesc().equals(oldJob.getStrJobDesc()) && newJob.getIntJobStatus() == oldJob.getIntJobStatus()){
					//do nothing
				}
				else if(newJob.getStrJobDesc().equals(oldJob.getStrJobDesc()) && newJob.getIntJobStatus() != oldJob.getIntJobStatus()){
					updatedList.add(newJob);
				}
				else if(intInner == oldJobs.size()){
					updatedList.add(newJob);
				}
			}
		}
		
		return updatedList;
	}

	public List<Job> convertToJob(List<String> stringJob){
		List<Job> jobList = new ArrayList<Job>();
		
		for(int i = 0; i < stringJob.size(); i++){
			
			jobList.add(new Job(stringJob.get(i), 1));
			
		}
		
		return jobList;
	}
}
