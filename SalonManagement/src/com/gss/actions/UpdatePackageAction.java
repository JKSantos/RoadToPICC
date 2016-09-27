package com.gss.actions;

import java.util.ArrayList;
import java.util.List;

import com.gss.dao.PackageJDBCRepository;
import com.gss.model.Package;
import com.gss.model.Product;
import com.gss.model.ProductPackage;
import com.gss.model.Service;
import com.gss.model.ServicePackage;
import com.gss.service.PackageService;
import com.gss.service.PackageServiceImpl;
import com.gss.service.ProductService;
import com.gss.service.ProductServiceImpl;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;
import com.gss.utilities.PackageHelper;
import com.gss.utilities.ItemDecoder;

public class UpdatePackageAction {
	
	private String intUpdatePackageID;
	ItemDecoder decode = new ItemDecoder();
	private String strUpdatePackageName;
	private String strUpdatePackageDesc;
	private List<Integer> intUpdatePackageType = new ArrayList<Integer>();
	private double dblUpdatePackagePrice;
	private String updatePackServType = "";
	private String updatePackProdType = "";
	private String updatePackServQty = "";
	private String updatePackProdQty = "";
	
	private String result;
	
	public String execute(){		
		
		ServiceService serviceService = new ServiceServiceImpl();
		ProductService productService = new ProductServiceImpl();
		PackageService packageService = new PackageServiceImpl();
		
		List<Service> service = serviceService.getAllService();
		List<Product> product = productService.getAllProducts();
		
		List<ServicePackage> serviceList = new ArrayList<ServicePackage>();
		List<ProductPackage> productList = new ArrayList<ProductPackage>();
		
		String[] selectedServices = this.updatePackServType.split(", ");
		String[] selectedProducts = this.updatePackProdType.split(", ");
		String[] serviceCount = this.updatePackServQty.split(", ");
		String[] productCount = this.updatePackProdQty.split(", ");
		
		String strType = String.valueOf(this.intUpdatePackageType.get(0));
		List<Integer> typeList = new ArrayList<Integer>();
		
		
		for(int i = 0; i < strType.length(); i++){
			typeList.add(Integer.parseInt(String.valueOf(strType.charAt(i))));
		}
	
		if(!updatePackServType.equals("")){
			
			String[] serviceOrder = decode.serviceOrderByChecked(service, selectedServices);
			String[] serviceQuantity = decode.getServiceQuantity(serviceOrder, serviceCount);
			
			for(int i = 0; i < selectedServices.length; i++){
				for(int j = 0; j < service.size(); j++){
					
					Service sample = service.get(j);
					if(Integer.parseInt(selectedServices[i]) == sample.getIntServiceID()){
						serviceList.add(new ServicePackage(1, 1, sample, Integer.parseInt(serviceCount[i]), 1));
					}
				}
			}
		}
		
		if(!updatePackProdType.equals("")){
			
			String[] productOrder = decode.productOrderByChecked(product, selectedProducts);
			
			String[] productQuantity = decode.getProductQuantity(productOrder, productCount);
			
			for(int i = 0; i < selectedProducts.length; i++){
				for(int j = 0; j < product.size(); j++){
					
					Product sample = product.get(j);
					if(Integer.parseInt(selectedProducts[i]) == sample.getIntProductID()){
						productList.add(new ProductPackage(1, 1, sample, Integer.parseInt(productCount[i]), 1));
					}
				}
			}
		}
		
		int type = PackageHelper.convertToSingleInt(typeList);
		
		
		Package packagee = new Package(Integer.parseInt(intUpdatePackageID), strUpdatePackageName.toUpperCase().trim(), strUpdatePackageDesc.toUpperCase().trim(), type, 1, "NON-EXPIRY", getDblUpdatePackagePrice(), serviceList, productList, 1);
	
		if(PackageJDBCRepository.checkPackageName(this.strUpdatePackageName.trim(), Integer.parseInt(this.intUpdatePackageID)).equalsIgnoreCase("valid")){
			if(packageService.updatePackage(packagee)){
				result = "success";
				return "success";
			}else{
				result = "failed";
				return "failed";
			}	
		}else{
			
			result = "existing";
			return result;
		}
	}

	public String getIntUpdatePackageID() {
		return intUpdatePackageID;
	}

	public void setIntUpdatePackageID(String intUpdatePackageID) {
		this.intUpdatePackageID = intUpdatePackageID;
	}

	public String getStrUpdatePackageName() {
		return strUpdatePackageName;
	}

	public void setStrUpdatePackageName(String strUpdatePackageName) {
		this.strUpdatePackageName = strUpdatePackageName;
	}

	public String getStrUpdatePackageDesc() {
		return strUpdatePackageDesc;
	}

	public void setStrUpdatePackageDesc(String strUpdatePackageDesc) {
		this.strUpdatePackageDesc = strUpdatePackageDesc;
	}

	public List<Integer> getIntUpdatePackageType() {
		return intUpdatePackageType;
	}

	public void setIntUpdatePackageType(List<Integer> intUpdatePackageType) {
		this.intUpdatePackageType = intUpdatePackageType;
	}

	public double getDblUpdatePackagePrice() {
		return dblUpdatePackagePrice;
	}

	public void setDblUpdatePackagePrice(double dblUpdatePackagePrice) {
		this.dblUpdatePackagePrice = dblUpdatePackagePrice;
	}

	public String getUpdatePackServType() {
		return updatePackServType;
	}

	public void setUpdatePackServType(String updatePackServType) {
		this.updatePackServType = updatePackServType;
	}

	public String getUpdatePackProdType() {
		return updatePackProdType;
	}

	public void setUpdatePackProdType(String updatePackProdType) {
		this.updatePackProdType = updatePackProdType;
	}

	public String getUpdatePackServQty() {
		return updatePackServQty;
	}

	public void setUpdatePackServQty(String updatePackServQty) {
		this.updatePackServQty = updatePackServQty;
	}

	public String getUpdatePackProdQty() {
		return updatePackProdQty;
	}

	public void setUpdatePackProdQty(String updatePackProdQty) {
		this.updatePackProdQty = updatePackProdQty;
	}

	public String getResult() {
		return result;
	}

}

