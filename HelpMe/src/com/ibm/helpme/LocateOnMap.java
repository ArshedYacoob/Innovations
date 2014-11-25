package com.ibm.helpme;

import java.util.StringTokenizer;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class LocateOnMap extends FragmentActivity {
	
	TextView textLocation;
	String locations;
	String userLattitude;
	String userLongitude;
	float userLat;
	float userLong;
	private LatLng USER_LOCATION;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_locate_on_map);
		
		locations = getIntent().getStringExtra("LocationKey");
		StringTokenizer tokenizer = new StringTokenizer(locations, "|");
		
		if (tokenizer.hasMoreElements()) {
			userLattitude = tokenizer.nextToken();
			userLongitude = tokenizer.nextToken();
		}
		
		Log.w("HelpMe", "User Lattitue: "+userLattitude);
		Log.w("HelpMe", "User Longitude: "+userLongitude);
		
		userLat = Float.parseFloat(userLattitude);
		userLong = Float.parseFloat(userLongitude);
		
		USER_LOCATION = new LatLng(userLat, userLong);
		Log.w("HelpMe", "New LatLng Variable Created");
		//Log.w("HelpMe", USER_LOCATION);
		
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		GoogleMap map = mapFragment.getMap();
		Log.w("HelpMe", "Fragment Initialized");
		
		map.addMarker(new MarkerOptions().position(USER_LOCATION).title("I Am Here"));
		Log.w("HelpMe", "Marker Added");
		
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		Log.w("HelpMe", "Map Type Set");
		
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(USER_LOCATION, 17);
		Log.w("HelpMe", "CameraUpdate Object updated with Location");
		
		map.animateCamera(update);
		Log.w("HelpMe", "View Moved to Location");

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.locate_on_map, menu);
		return true;
	}
	
		
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
