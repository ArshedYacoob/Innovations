package com.ibm.services;

import com.ibm.helpme.ReadGPSLocation;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class AccelerometerService extends Service {
	
	Sensor accelerometer;
	SensorManager sm;
	TextView acceleration;
	double finalAcceleration, x, y, z, totalAcceleration;
	
	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//Toast.makeText(getApplicationContext(), "Background Service has Started", Toast.LENGTH_SHORT).show();
		Log.w("HelpMe", "Accelerometer thread starting");
		FirstThread firstThread = new FirstThread();
		firstThread.start();
		return START_STICKY;	
	}

	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
	}
	
	public class FirstThread extends Thread implements SensorEventListener {
		
		public void run() {
			
			Log.w("HelpMe", "Inside Accelerometer Thread");
			sm = (SensorManager)getSystemService(SENSOR_SERVICE);
			Log.w("HelpMe", "Sensor Manager Object Created");
	        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	        Log.w("HelpMe", "Sensor Registered with Accelerometer");
	        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	        Log.w("HelpMe", "Listener Object Created and Registered with Sensor Manager");
		}

		@Override
		public void onSensorChanged(SensorEvent event) {
			// TODO Auto-generated method stub
			Log.w("HelpMe", "Calculating Acceleration");
			x= event.values[0]*event.values[0];
			y= event.values[1]*event.values[1];
			z= event.values[2]*event.values[2];
			totalAcceleration=x+y+z;
			finalAcceleration=Math.sqrt(totalAcceleration);
			Log.w("HelpMe", "Acceleration Calculated");
			Log.w("HelpMe", Double.toString(finalAcceleration));
			
			if(finalAcceleration>= 20) {
				//Toast.makeText(getApplicationContext(), "Mobile Position Changed", Toast.LENGTH_SHORT).show();
				
				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_LAUNCHER);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				ComponentName cn = new ComponentName(getApplicationContext(), ReadGPSLocation.class);
				intent.setComponent(cn);
				startActivity(intent);
				
				
			}
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			// TODO Auto-generated method stub
			
		}
	}

}
