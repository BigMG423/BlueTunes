package com.cs117;

import java.util.LinkedList;

import android.app.TabActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

public class FindDeviceActivity extends TabActivity
{
	private View devicesView; // list of devices in area
	private ListView availableDevicesList, connectedDevicesList;
	private ArrayAdapter<String> devicesFound, devicesConnected;
	private TabHost tabHost;
	//private Button connectButton, disconnectButton, disconnectAllButton;
	private clickListener cl = new clickListener();
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		try
		{
			LayoutInflater inflater = this.getLayoutInflater();
			devicesView = inflater.inflate(R.layout.devicesview, null);
			
			((Button) devicesView.findViewById(R.id.connectbutton)).setOnClickListener(cl);
			((Button) devicesView.findViewById(R.id.disconnectbutton)).setOnClickListener(cl);
			((Button) devicesView.findViewById(R.id.disconnectallbutton)).setOnClickListener(cl);
			
			setContentView(devicesView);
	
			devicesFound = new ArrayAdapter<String>(this, R.layout.checkedtextitem);
			devicesConnected = new ArrayAdapter<String>(this, R.layout.checkedtextitem);
			
			tabHost = getTabHost();
			
			tabHost.addTab(tabHost.newTabSpec("available_tab").setIndicator("Available Devices").setContent(R.id.availabledeviceslayout));
			tabHost.addTab(tabHost.newTabSpec("connected_tab").setIndicator("Connected Devices").setContent(R.id.connecteddeviceslayout));
			
			tabHost.setCurrentTab(0);
			
			
			availableDevicesList = (ListView) devicesView.findViewById(R.id.availabledeviceslist);
			//availableDevicesList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
			
			availableDevicesList.setAdapter(devicesFound);
			
			connectedDevicesList = (ListView) devicesView.findViewById(R.id.connecteddeviceslist);
			//connectedDevicesList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
			
			connectedDevicesList.setAdapter(devicesConnected);
			
			devicesFound.add("There can be only one Highlander");
			devicesFound.add("Item two");
			devicesFound.add("and three");
			devicesFound.add("fourth");
			devicesFound.add("Cinco de Mayo!");
			devicesFound.add("revenge of the sixth");
			devicesFound.add("lucky number seven");
			devicesFound.add("magic 8 ball");
			devicesFound.add("niners");
			devicesFound.add("base 10");
			devicesFound.add("eleven has too many syllables");
			
			devicesConnected.add("I got one too");
			
		} catch (Exception e)
		{
			//((Button) devicesView.findViewById(R.id.connectbutton)).setText(e.toString());
		}
		
	}
	
	private class clickListener implements View.OnClickListener
	{
		@Override
		public void onClick(View v) {
			switch (v.getId())
			{
			case R.id.connectbutton:
				for (int i = 0; i < availableDevicesList.getCount(); i++)
					if (availableDevicesList.isItemChecked(i))
					{
						// connect to item, remove from this list and put on the connected list
						
					}
				break;
			case R.id.disconnectbutton:
				break;
			case R.id.disconnectallbutton:
				break;
			}
		}
		
	}
}