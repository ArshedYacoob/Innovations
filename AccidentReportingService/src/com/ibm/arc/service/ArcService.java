package com.ibm.arc.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.ibm.arc.util.DBUtil;

@Path("/alertDetails")
public class ArcService {

	@GET
	@Produces({MediaType.TEXT_PLAIN})
	public String getLocation(@QueryParam("deviceId") String deviceId) throws SQLException
	{
		String locations = null;
		Statement stmt1 = DBUtil.getStatement();
		String sqlStatement1 = null;
		
		String userLattitude = null;
		String userLongitude = null;
		
		sqlStatement1 = "SELECT l_816338.SUBSCRIBERDATA.USERLONGITUDE,l_816338.SUBSCRIBERDATA.USERLATTITUDE FROM l_816338.SUBSCRIBERDATA WHERE l_816338.SUBSCRIBERDATA.REGISTEREDUSERDEVICEID='"+deviceId+"'";
		
		ResultSet resultSet1 = stmt1.executeQuery(sqlStatement1);
		while (resultSet1.next()) {
		userLattitude = resultSet1.getString("USERLATTITUDE");
		userLongitude = resultSet1.getString("USERLONGITUDE");
		}
		
		locations = userLattitude+"|"+userLongitude;
		
		return locations;
	}	
	
	
	@POST
	@Consumes({MediaType.TEXT_PLAIN})
	public void updateAlertDetails(String alertDetails) throws SQLException
	{
		Statement stmt1 = DBUtil.getStatement();
		String sqlStatement1 = null;
		String registeredUserDeviceId = null;
		String userLattitude = null;
		String userLongitude = null;
		
		StringTokenizer tokenizer = new StringTokenizer(alertDetails, "|");

		if (tokenizer.hasMoreElements()) {
			registeredUserDeviceId = tokenizer.nextToken();
			userLattitude = tokenizer.nextToken();
			userLongitude = tokenizer.nextToken();
		}
		
		
		sqlStatement1 = "UPDATE l_816338.SUBSCRIBERDATA SET USERLATTITUDE='"+userLattitude+"', USERLONGITUDE='"+userLongitude+"' WHERE REGISTEREDUSERDEVICEID='"+registeredUserDeviceId+"'";
		
		stmt1.executeUpdate(sqlStatement1);
		
		System.out.println("Success");
		
	}
	
}
