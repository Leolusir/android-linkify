package com.weibotextutil.demo;

/* Android textview text-to-face
 * Copyright (c) 2014 leo <leo.lusir@gmail.com>
 * https://github.com/Leolusir  
 * */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.weibotextutil.R;
import com.weibotextutil.libs.TextAddImg;
import com.weibotextutil.libs.TextAddLink;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify.MatchFilter;
import android.text.util.Linkify.TransformFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/*
 * This is an example
 * do not forget to add content to the manifest
 * */

public class MainActivity extends Activity {
	private TextView textview;
	String text = "#Topic# hello everyone,hello,hello[smile], @ross together and play here";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textview = (TextView)findViewById(R.id.textView1);
		textview.setText(text);
		//text-to-link
		addLinkToText(textview);
		//text-to-face
		addfaceImg(textview, this);
	}
	
	private void addLinkToText(TextView v){
		v.setAutoLinkMask(0);
		Pattern mentionsPattern = Pattern.compile("@(\\w+?)(?=\\W|$)(.)");
		String mentionsScheme = String.format("%s/?%s=", Constants.USER_SCHEMA, Constants.USER_PARAM_UID);

		TextAddLink.addLinks(v, mentionsPattern, mentionsScheme, new MatchFilter() {
			//
			@Override
			public boolean acceptMatch(CharSequence s, int start, int end) {
	
				return s.charAt(end-1) !='.';
			}
	
			}, new TransformFilter() {
			@Override
				public String transformUrl(Matcher match, String url) {
					Log.d("", match.group(1));
					return url.toString(); 
				}
		});		

		Pattern trendsPattern = Pattern.compile("#(\\w+?)#");
		String trendsScheme = String.format("%s/?%s=", Constants.topic_SCHEMA, Constants.topic_PARAM_UID);
		TextAddLink.addLinks(v, trendsPattern, trendsScheme, null, new TransformFilter() {
			@Override
			public String transformUrl(Matcher match, String url) {
				Log.d("", match.group(1));
				return url.toString(); 
			}
		});
		
		v.setMovementMethod(LinkMovementMethod.getInstance());
	}
	
	private void addfaceImg(TextView v, Context context){
		Pattern mentionsPattern = Pattern.compile("\\[.+?\\]");
		TextAddImg.transformImg(v, mentionsPattern, context);
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
