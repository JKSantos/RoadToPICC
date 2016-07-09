package com.gss.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.Catalogue;
import com.gss.model.Service;
import com.gss.service.ServiceService;
import com.gss.service.ServiceServiceImpl;

public class CatalogueJDBCRepository implements CatalogueRepository{

	private JDBCConnection jdbc = new JDBCConnection();
	
	@Override
	public List<Catalogue> getAllCatalogue() {
		
		Connection con = jdbc.getConnection();
		ServiceService service = new ServiceServiceImpl();
		List<Service> serviceList = service.getAllService();
		
		String query = "CALL getAllCatalogue()";
		List<Catalogue> catList = new ArrayList<Catalogue>();
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			ResultSet set = pre.executeQuery();
			
			while(set.next()){
				int intID = set.getInt(1);
				String serv = set.getString(2);
				String cat = set.getString(3);
				Blob blob = set.getBlob(4);
				int blobLength = (int)blob.length();
				int status = set.getInt(5);
				byte[] pic = blob.getBytes(1, blobLength);
				double price = 0;
				
				for(int i = 0; i < serviceList.size(); i++){
					Service s = serviceList.get(i);
					
					if(s.getStrServiceName().equals(serv)){
						price = s.getDblServicePrice();
						break;
					}
				}
				
				Catalogue catalogue = new Catalogue(intID, cat, serv, price, pic, null, status);
				catList.add(catalogue);
			}
			
			pre.close();
			set.close();
			con.close();
			
			return catList;
			
		}
		catch(Exception e){
			System.out.println(e.fillInStackTrace());
			return null;
		}
	}

	@Override
	public boolean createCatalogue(Catalogue catalogue) {
		
		Connection con = jdbc.getConnection();
		String query = "CALL createCatalogue(?, ?, ?)";
		
		try{
			
			File imageFile = new File(catalogue.getStrCataloguePath());
			FileInputStream fis = new FileInputStream(imageFile);
			
			PreparedStatement pre = con.prepareStatement(query);
			pre.setString(1, catalogue.getStrCatalogueType());
			pre.setString(2, catalogue.getStrCatalogueName());
			pre.setBinaryStream(3, (InputStream)fis, (int)imageFile.length());
			
			pre.execute();
			pre.close();
			con.close();

			return true;
		}
		catch(Exception e){
			System.out.println(e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public boolean updateCatalogue(Catalogue catalogue) {
		
		Connection con = jdbc.getConnection();
		String query = "CALL updateCatalogue(?, ?, ?, ?)";
		
		try{
			PreparedStatement pre = con.prepareStatement(query);
					
			if(!catalogue.getStrCataloguePath().equalsIgnoreCase("image")){
				File imageFile = new File(catalogue.getStrCataloguePath());
				FileInputStream fis = new FileInputStream(imageFile);
				
				pre.setInt(1, catalogue.getIntCatalogueID());
				pre.setString(2, catalogue.getStrCatalogueType());
				pre.setString(3, catalogue.getStrCatalogueName());
				pre.setBinaryStream(4, (InputStream)fis, (int)imageFile.length());
			}
			else{
				pre.setInt(1, catalogue.getIntCatalogueID());
				pre.setString(2, catalogue.getStrCatalogueType());
				pre.setString(3, catalogue.getStrCatalogueName());
				pre.setInt(4, 101);
			}
			
			pre.execute();
			pre.close();
			con.close();

			return true;
		}
		catch(Exception e){
			System.out.println(e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public boolean deactivateCatalogue(int intCatalogueID) {
		
		Connection con = jdbc.getConnection();
		String query = "UPDATE tblCatalogue SET intStatus = 0 WHERE intCatalogueID = ?";
		
		try{
			PreparedStatement pre = con.prepareStatement(query);
			pre.setInt(1, intCatalogueID);

			pre.execute();
			pre.close();
			con.close();
			
			return true;
		}
		catch(Exception e){
			System.out.println(e.fillInStackTrace());
			return false;
		}
	}
	
	

}
