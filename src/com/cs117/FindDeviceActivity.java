package com.cs117;

import java.util.LinkedList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

public class FindDeviceActivity extends ListActivity
{
	private View devicesView; // list of devices in area
	private ListView deviceList;
	private ArrayAdapter<CheckedTextView> devicesFound;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		try
		{
			LayoutInflater inflater = this.getLayoutInflater();
			devicesView = inflater.inflate(R.layout.devicesview, null);
	
			/*
			devicesFound = new ArrayAdapter<CheckedTextView>(this, R.layout.devicesview);
			
			deviceList = (ListView) devicesView.findViewById(android.R.id.list);
			deviceList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
			deviceList.setAdapter(devicesFound);
			
			devicesFound.add(new CheckedTextView(this));
			*/
		} catch (Exception e)
		{
			((TextView) devicesView.findViewById(R.id.availabledevicestext)).setText(e.toString());
		}
		
		setContentView(devicesView);
	}
}