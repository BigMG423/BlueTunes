package com.cs117;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore.Audio;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AlphabetIndexer;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class PlayMusicActivity extends Activity
{
	private BlueTunes app;
	private View musicView;
	private ListView musicList;
	
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		try {
			LayoutInflater inflater = this.getLayoutInflater();
			musicView = inflater.inflate(R.layout.playmusic, null);
			
			Log.i("MG", "play music on create");
			
			app = (BlueTunes) getApplication();
			
			musicList = (ListView) musicView.findViewById(R.id.musiclist);
			musicList.setFastScrollEnabled(true);
			musicList.setOnItemClickListener(new itemClickListener());
			
			
			ListAdapter musicAdapter = new AlphaCursorAdapter(this, android.R.layout.two_line_list_item, app.cur,
					new String[] {Audio.AudioColumns.TITLE, Audio.AudioColumns.ARTIST},
					new int[] {android.R.id.text1, android.R.id.text2});
			
			musicList.setAdapter(musicAdapter);
			
			setContentView(musicView);
		
		} catch (Exception e)
		{
			Toast.makeText(this, e.toString() + "\n" + e.getStackTrace()[0] + "\n" + e.getStackTrace()[1], Toast.LENGTH_LONG).show();
		}
		Log.i("MG", "play music on create finish");
	}
	
	class AlphaCursorAdapter extends SimpleCursorAdapter implements SectionIndexer
	{
		AlphabetIndexer alphaIndexer;
		public AlphaCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to)
		{
			super(context, layout, c, from, to);
			
			alphaIndexer = new AlphabetIndexer(c, c.getColumnIndex(Audio.AudioColumns.TITLE), " ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		}

		public int getPositionForSection(int section)
		{
			return alphaIndexer.getPositionForSection(section);
		}

		public int getSectionForPosition(int position)
		{
			return alphaIndexer.getSectionForPosition(position);
		}

		public Object[] getSections()
		{
			return alphaIndexer.getSections();
		}
		
	};
	
	private class itemClickListener implements AdapterView.OnItemClickListener
    {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
			Log.i("MG", "on list click");
			BlueTunes.arrLoc = position;
			//app.cur.moveToPosition(position);
			try
			{
				if (app.player.isPlaying())
				{
					app.player.stop();
					app.player.reset();
				}
				app.player.setDataSource(app.arr.get(position).mData);
				//app.player.setDataSource(app.cur.getString(app.cur.getColumnIndex(Audio.Media.DATA)));
				app.player.prepare();
				app.player.start();
				//Integer i = app.cur.getPosition();
				String s = "click pos field, id field, cur_id:";
				s += "  " + position + "  " + id + "  " + app.arr.get(position).mId;
				Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
			} catch (Exception e)
			{
				// meh
			}
			//Toast.makeText(getApplicationContext(), cur.getString(cur.getColumnIndex(Audio.Media.DATA)), Toast.LENGTH_LONG).show();
			Log.i("MG", "on list click end");
			
		}
    	
    };
}