package com.ibm.helpme;

import com.ibm.utility.SendLocationDetails;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ReadGPSLocation extends ActionBarActivity {

	TextView textLattitude;
	TextView textLongitude;
	String msg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_co__ordinates);

		textLattitude = (TextView) findViewById(R.id.textLat);
		textLongitude = (TextView) findViewById(R.id.textLon);
		
				
		Log.w("HelpMe", "Running Read Co-ordinates Thread");
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Log.w("HelpMe", "Created LocationManager Object");
		LocationListener ll = new myLocationListener();
		Log.w("HelpMe", "Created LocationListener Object");
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 20000, 5, ll);
		Log.w("HelpMe", "Calling LocationListener");

	}

	private class myLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			if (location != null) {

				Log.w("HelpMe", "Inside Location Listener");
				double pLat = location.getLatitude();
				double pLong = location.getLongitude();

				textLattitude.setText(Double.toString(pLat));
				textLongitude.setText(Double.toString(pLong));

				// String id = Secure.getString(getContentResolver(),
				// Secure.ANDROID_ID);
				Log.w("HelpMe", "Retriving Co-ordinates Complete");
				String id = "f5b965aa54b9d57e";
				String pLat1 = Double.toString(pLat);
				String pLong1 = Double.toString(pLong);
				;
				msg = id + "|" + pLat1 + "|" + pLong1;

			}
			
			Toast.makeText(getApplicationContext(), "Sending Location...", Toast.LENGTH_LONG).show();
			
			Log.w("HelpMe", msg);
			SendLocationDetails sld = new SendLocationDetails(msg);
			sld.start();
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.read_co__ordinates, menu);
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
