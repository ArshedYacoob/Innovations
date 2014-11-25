package com.ibm.services;

import com.ibm.helpme.ReadGPSLocation;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class SendLocationService extends Service {

	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		FirstThread firstThread = new FirstThread();
		firstThread.start();
		//Toast.makeText(getApplicationContext(), "Background Service has Started", Toast.LENGTH_SHORT).show();
		return START_STICKY;	
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
	}
	
	public class FirstThread extends Thread {
		
		public void run() {
			
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			ComponentName cn = new ComponentName(getApplicationContext(), ReadGPSLocation.class);
			intent.setComponent(cn);
			startActivity(intent);
			
		}

	}


}
