package com.gss.utilities;

import java.util.ArrayList;
import java.util.List;

import com.gss.model.Product;

public class GetProductListThread implements Runnable{
	
	private Thread thread;
	private List<Product> productList = new ArrayList<Product>();
	
	public List<Product> getProducts(){
		
		start();
		
		return this.productList;
	}
	
	
	public void start(){
		
		if(thread == null){
			thread = new Thread(this, "Product");
			thread.start();
		}
		else{
			thread.start();
		}
	}


	@Override
	public void run() {
		
		this.productList = Product.getAllProduct();
		try {
			thread.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
