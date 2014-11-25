package com.ibm.utility;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.os.Message;
import android.util.Log;

public class SendLocationDetails extends Thread{
	
	String msg;
	
	public SendLocationDetails(String msg) {
		
		this.msg = msg;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// Create and populate a simple object to be used in the request

		Log.w("HelpMe", "Sending Location Co-Ordinates");
		// Set the Content-Type header
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(new MediaType("text","plain"));
		HttpEntity<Message> requestEntity = new HttpEntity(msg);

		// Create a new RestTemplate instance
		RestTemplate restTemplate = new RestTemplate();

		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

		// Make the HTTP POST request, marshaling the request to JSON, and the response to a String
		ResponseEntity<String> responseEntity = restTemplate.exchange("http://accidentreportingservice.mybluemix.net/webservices/alertDetails", HttpMethod.POST, requestEntity, String.class);
		String result = responseEntity.getBody();	
		
		Log.w("HelpMe", "Location Co-Ordinates Sent");	
		
	}
	
}

		