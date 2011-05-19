package com.cs117;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity
{
	private BlueTunes app;
	
	private View mainView; // main start screen
	private clickListener cl = new clickListener();
	
	public static final String TOAST = "toast";
	// Local Bluetooth adapter    
	private BluetoothAdapter mBluetoothAdapter = null;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        app = (BlueTunes) getApplication();
        
        
        LayoutInflater inflater = this.getLayoutInflater();
		mainView = inflater.inflate(R.layout.main, null);
		
		((Button) mainView.findViewById(R.id.finddevices)).setOnClickListener(cl);
		
		((Button) mainView.findViewById(R.id.playmusic)).setOnClickListener(cl);
		
		((Button) mainView.findViewById(R.id.stopbutton)).setOnClickListener(cl);
		
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
    
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	MenuInflater inf = getMenuInflater();
    	inf.inflate(R.menu.menu, menu);
    	return true;
    }
    
    public void onPause()
    {
    	super.onPause();
    	if (app.player.isPlaying())
    		moveTaskToBack(true);
    }
    
    public void onBackPressed()
    {
    	if (app.player.isPlaying())
    		moveTaskToBack(true);
    	else
    		finish();
    }
	
	public void onDestroy()
	{
		super.onDestroy();
		app.cur.close();
		if (app.player != null)
		{
			app.player.release();
			app.player = null;
		}
	}
    
    private void findDevices()
    {
    	startActivity(new Intent(this, FindDeviceActivity.class));
    }
    
    private void playMusic()
    {
    	startActivity(new Intent(this, PlayMusicActivity.class));
    }
    
    private class clickListener implements View.OnClickListener
    {
		//@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.finddevices:
				findDevices();
				break;
			case R.id.playmusic:
				playMusic();
				break;
			case R.id.stopbutton:
				Toast.makeText(getApplicationContext(), "stop button", Toast.LENGTH_LONG).show();
				app.player.stop();
				app.player.reset();
			}
		}
    };
}
