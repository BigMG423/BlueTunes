package com.cs117;

import java.util.ArrayList;

import android.app.Application;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio;
import android.util.Log;

public class BlueTunes extends Application
{
	public MediaPlayer player;
	public Cursor cur;
	
	public ArrayList<DBdata> arr = new ArrayList<DBdata>();
	public static int arrLoc = -1;
	
	public void onCreate()
	{
		super.onCreate();
		//Audio.Media.TITLE
        cur = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, Audio.Media.TITLE);
        
        cur.moveToFirst();
        while (!cur.isAfterLast())
        {
        	arr.add(new DBdata(cur.getString(cur.getColumnIndex(Audio.Media._ID)),
        			cur.getString(cur.getColumnIndex(Audio.Media.DATA)),
        			cur.getString(cur.getColumnIndex(Audio.Media.ARTIST)),
        			cur.getString(cur.getColumnIndex(Audio.Media.ALBUM)),
        			cur.getString(cur.getColumnIndex(Audio.Media.TITLE))));
        	cur.moveToNext();
        }
        
        player = new MediaPlayer();
		player.setOnCompletionListener(new mediaCompletionListener());
		
		Log.i("MG", "done with app oncreate");
	}
	
	private class mediaCompletionListener implements MediaPlayer.OnCompletionListener
    {
		public void onCompletion(MediaPlayer mp)
		{
			Log.i("MG", "on complete");
			mp.reset();
			arrLoc += 1;
			try
			{
				mp.setDataSource(arr.get(arrLoc).mData);
				mp.prepare();
				mp.start();
			} catch (Exception e)
			{
				// fun media player errors
			}
			Log.i("MG", "finished on complete");
		}
    };
    
    class DBdata
    {
    	String mId;
    	String mData;
    	String mArtist;
    	String mAlbum;
    	String mTitle;
    	
    	DBdata(String id, String data, String artist, String album, String title)
    	{
    		mId = id;
    		mData = data;
    		mArtist = artist;
    		mAlbum = album;
    		mTitle = title;
    	}
    }
}