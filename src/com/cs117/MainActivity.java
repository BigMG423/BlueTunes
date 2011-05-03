package com.cs117;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity
{
	private View mainView; // main start screen
	
	private Button findDevicesButton = null;
	private Button playMusicButton = null;
	
	private clickListener cl = new clickListener();
	
	
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
		
        setContentView(mainView);
    }
    
    private void findDevices()
    {
    	startActivity(new Intent(this, FindDeviceActivity.class));
    }
    
    private class clickListener implements View.OnClickListener
    {
		@Override
		public void onClick(View v)
		{
			if (v.getId() == R.id.finddevices)
				findDevices();
		}
    	
    };
}