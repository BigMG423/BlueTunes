package com.cs117;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity
{
	private View mainView; // main start screen
	
	private Button findDevicesButton = null;
	private Button playMusicButton = null;
	private Button remoteMp3ModeButton = null;
	
	//private clickListener cl = new clickListener();
	
	public static final String TOAST = "toast";
	// Local Bluetooth adapter    
	private BluetoothAdapter mBluetoothAdapter = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        LayoutInflater inflater = this.getLayoutInflater();
		mainView = inflater.inflate(R.layout.main, null);
		
		findDevicesButton = (Button) mainView.findViewById(R.id.finddevices);	
		findDevicesButton.setOnClickListener(cl);
		
		playMusicButton = (Button) mainView.findViewById(R.id.playmusic);
		playMusicButton.setOnClickListener(cl);
		
		remoteMp3ModeButton = (Button) mainView.findViewById(R.id.remotemp3mode);
		remoteMp3ModeButton.setOnClickListener(cl);
		
		
        setContentView(mainView);
        
        // Get local Bluetooth adapter        
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        // If the adapter is null, then Bluetooth is not supported        
        if (mBluetoothAdapter == null) {            
        	Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();            
        	finish();            
        	return;        
        	}
    }
    
    private void findDevices()
    {
    	startActivity(new Intent(this, FindDeviceActivity.class));
    }
    
    private void remoteMp3Mode()
    {
    	startActivity(new Intent(this, RemoteMp3ModeActivity.class));
    }
    
    //private class clickListener implements View.OnClickListener
    View.OnClickListener cl = new View.OnClickListener() {
		
		//@Override
		public void onClick(View v)
		{
			if (v.getId() == R.id.finddevices)
				findDevices();
			else if (v.getId() == R.id.remotemp3mode)
				remoteMp3Mode();	
		}
    };
    	
}