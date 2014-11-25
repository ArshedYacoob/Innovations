package com.ibm.utility;

import java.util.StringTokenizer;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.ibm.helpme.LocateOnMap;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class RetrieveLocationDetails extends Thread{
	
	String userLattitude;
	String userLongitude;
	String msg;
	String id;
	private Context mContext;

	public RetrieveLocationDetails(Context context, String id) {

		this.id = id;
		mContext=context;
	}

	
	@Override
	public void run() {
		final String url = "http://accidentreportingservice.mybluemix.net/webservices/alertDetails?deviceId="+id;
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(
				new StringHttpMessageConverter());
		String locations = restTemplate.getForObject(url, String.class);
		StringTokenizer tokenizer = new StringTokenizer(locations, "|");
		
		if (tokenizer.hasMoreElements()) {
			userLattitude = tokenizer.nextToken();
			userLongitude = tokenizer.nextToken();
		}
		
		msg = userLattitude+"|"+userLongitude;
		Log.w("HelpMe", msg);
		
		Intent intent = new Intent(mContext, LocateOnMap.class);
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		ComponentName cn = new ComponentName(mContext, LocateOnMap.class);
		intent.setComponent(cn);
		intent.putExtra("LocationKey", locations);
		mContext.startActivity(intent);
		
	}

}
