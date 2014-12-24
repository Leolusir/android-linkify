package com.example.weibotextutil;

/* Android textview text-to-face
 * Copyright (c) 2014 leo <leo.lusir@gmail.com>
 * https://github.com/Leolusir  
 * */

import com.weibotextutil.demo.Constants;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class UserActivity extends Activity {
	private static final Uri PROFILE_URI = Uri.parse(Constants.USER_SCHEMA);
	String username;
	
	private TextView textview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		
		textview = (TextView)findViewById(R.id.user_text);
		
		extractUidFromUri();
	}
	
	private void extractUidFromUri() {
		Uri uri = getIntent().getData();
        if (uri != null && PROFILE_URI.getScheme().equals(uri.getScheme())) {
        	username = uri.getQueryParameter(Constants.USER_PARAM_UID);
             Log.d("", "name from url: " + username);
             textview.setText(username);
        }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user, menu);
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
