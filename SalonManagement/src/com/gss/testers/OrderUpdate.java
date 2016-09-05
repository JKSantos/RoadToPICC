package com.gss.testers;

import com.gss.actions.ProductSales.UpdateOrderAction;

public class OrderUpdate {
	
	public static void main(String[] args){
		UpdateOrderAction action = new UpdateOrderAction();
		
		action.setIntOrderID(70);
		action.setStrName("jepwi");
		action.setStrContactNo("111111111");
		action.setStrStreet("street");
		action.setIntLocationID(3);
		action.setOrderType("delivery");
		action.setSelectedProducts("33");
		action.setProductQuantity("4");
		action.setStrTotalPrice("150025");
		
		try {
			System.out.print(action.execute());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
