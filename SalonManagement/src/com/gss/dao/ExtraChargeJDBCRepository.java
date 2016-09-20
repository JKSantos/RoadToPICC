package com.gss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gss.connection.JDBCConnection;
import com.gss.model.ExtraCharge;

public class ExtraChargeJDBCRepository implements ExtraChargeRepository{

	private JDBCConnection jdbc = new JDBCConnection();
	
	@Override
	public boolean createExtraCharge(ExtraCharge extra) {

		Connection con = jdbc.getConnection();
		String query = "CALL createExtraCharge(?, ?, ?)";
		
		try{
			PreparedStatement pre = con.prepareStatement(query);
			pre.setString(1, extra.getStrECName());
			pre.setString(2, extra.getStrECDetails());
			pre.setDouble(3, extra.getDblECPrice());
			boolean isRecorded = pre.execute();
			
			pre.close();
			con.close();
			
			return isRecorded;
		}
		catch(Exception e){
			System.out.println(e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public List<ExtraCharge> getAllExtraCharges() {
		
		Connection con = jdbc.getConnection();
		String query = "SELECT * FROM tblExtraCharges WHERE intStatus = 1;";
		List<ExtraCharge> ecList = new ArrayList<ExtraCharge>();
		
		try{
			PreparedStatement pre = con.prepareStatement(query);
			ResultSet set = pre.executeQuery();
			
			while(set.next()){
				int intID = set.getInt(1);
				String name = set.getString(2);
				String desc = set.getString(3);
				double price = set.getDouble(4);
				int stat = set.getInt(5);
				
				ExtraCharge extra = new ExtraCharge(intID, name, desc, price, stat);
				extra.setStringPrice(String.format("%.2f", price));
				ecList.add(extra);
			}
			
			pre.close();
			set.close();
			con.close();
			
			return ecList;
			
		}
		catch(Exception e)
		{
			System.out.println(e.fillInStackTrace());
			return null;
		}
	}

	@Override
	public boolean updateExtraCharge(ExtraCharge extra) {
		
		Connection con = jdbc.getConnection();
		String query = "UPDATE tblExtraCharges SET strExtraChargeName = ?, strExtraChargeDesc = ?, dblExtraChargePrice = ? WHERE intExtraChargeID = ?;";
		
		try{
			PreparedStatement pre = con.prepareStatement(query);
			pre.setString(1, extra.getStrECName());
			pre.setString(2, extra.getStrECDetails());
			pre.setDouble(3, extra.getDblECPrice());
			pre.setInt(4, extra.getIntECID());
			
			pre.execute();
			pre.close();
			con.close();
			
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public boolean deactivateExtraCharge(int intExtraChargeID) {
		
		Connection con = jdbc.getConnection();
		String query = "UPDATE tblExtraCharges SET intStatus = 0 WHERE intExtraChargeID = ?;";
		
		try{
			PreparedStatement pre = con.prepareStatement(query);
			pre.setInt(1, intExtraChargeID);
			
			pre.execute();
			pre.close();
			con.close();
			
			return true;
		}
		catch(Exception e)
		{
			System.out.println(e.fillInStackTrace());
			return false;
		}
	}

	@Override
	public List<ExtraCharge> queryAllOtherCharge() {
		
		Connection con = jdbc.getConnection();
		String query = "SELECT * FROM tblExtraCharges ORDER BY strExtraChargeName ASC;";
		List<ExtraCharge> ecList = new ArrayList<ExtraCharge>();
		
		try{
			PreparedStatement pre = con.prepareStatement(query);
			ResultSet set = pre.executeQuery();
			
			while(set.next()){
				int intID = set.getInt(1);
				String name = set.getString(2);
				String desc = set.getString(3);
				double price = set.getDouble(4);
				int stat = set.getInt(5);
				
				ExtraCharge extra = new ExtraCharge(intID, name, desc, price, stat);
				extra.setStringPrice(String.format("%.2f", price));
				ecList.add(extra);
			}
			
			pre.close();
			set.close();
			con.close();
			
			return ecList;
			
		}
		catch(Exception e)
		{
			System.out.println(e.fillInStackTrace());
			return null;
		}
	}
	
	public static String checkExtraChargeName(String name){
		
		Connection con = new JDBCConnection().getConnection();
		
		String query = "CALL checkExtraChargeName(?);";
		
		String result = "";
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			pre.setString(1, name);
			
			ResultSet set = pre.executeQuery();
			
			while(set.next()){
				result = set.getString(1);
			}

			pre.close();
			set.close();
			con.close();
			
			return result;
			
		}catch(Exception e){
			e.printStackTrace();
			return "failed";
		}
	}
	
	public static String checkExtraChargeName(String name, int chargeID){
		
		Connection con = new JDBCConnection().getConnection();
		
		String query = "CALL checkExtraChargeNameWithID(?, ?);";
		
		String result = "";
		
		try{
			
			PreparedStatement pre = con.prepareStatement(query);
			pre.setString(1, name);
			pre.setInt(2, chargeID);
			
			ResultSet set = pre.executeQuery();
			
			while(set.next()){
				result = set.getString(1);
			}

			pre.close();
			set.close();
			con.close();
			
			return result;
			
		}catch(Exception e){
			e.printStackTrace();
			return "failed";
		}
	}

}
