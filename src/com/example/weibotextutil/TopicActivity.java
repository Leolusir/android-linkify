package com.example.weibotextutil;

/* Android textview text-to-face
 * Copyright (c) 2014 leo <leo.lusir@gmail.com>
 * https://github.com/Leolusir  
 * */

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class TopicActivity extends Activity {
	String topic;
	
	private TextView textview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_topic);
		
		textview = (TextView)findViewById(R.id.topic_text);
		extractUidFromUri();
		
	}
	
	private void extractUidFromUri() {
		Uri uri = getIntent().getData();
		String temp = uri.toString();
		String t[] = temp.split("#");
		topic = t[1].toString();
		textview.setText(topic);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.topic, menu);
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
