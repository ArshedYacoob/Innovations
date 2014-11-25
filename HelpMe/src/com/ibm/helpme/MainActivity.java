package com.ibm.helpme;

import com.ibm.utility.RetrieveLocationDetails;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.w("HelpMe", "Main Thread Started");
			
		final Button btnSend = (Button) findViewById(R.id.button1); 
	    btnSend.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View view) {                 
	            //add your code here..
	    		Intent myIntent = new Intent(MainActivity.this, ReadGPSLocation.class);
	    		//myIntent.putExtra("key", value); //Optional parameters
	    		MainActivity.this.startActivity(myIntent);
	            Toast.makeText(view.getContext(),"Send Clicked",Toast.LENGTH_SHORT).show();
	        }
	    });
	    
	    
	    final Button btnRetrieve = (Button) findViewById(R.id.button2); 
	    btnRetrieve.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View view) {                 
	            /*add your code here..
	    		Intent myIntent = new Intent(MainActivity.this, TempMapLocation.class);
	    		//myIntent.putExtra("key", value); //Optional parameters
	    		MainActivity.this.startActivity(myIntent);*/
	            Toast.makeText(view.getContext(),"Retrieve Clicked",Toast.LENGTH_SHORT).show();
	        	
          
	    		String id = "f5b965aa54b9d57e";
	    		RetrieveLocationDetails rld = new RetrieveLocationDetails(getApplicationContext(), id);
	    		rld.start();
	        }
	    });
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
