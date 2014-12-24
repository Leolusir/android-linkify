package com.weibotextutil.libs;

/* Android textview text-to-face
 * Copyright (c) 2014 leo <leo.lusir@gmail.com>
 * https://github.com/Leolusir  
 * */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.weibotextutil.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.TextView;

/*
 * To use the text-to-face, first import expression package
 * */

public class TextAddImg {

	public static final void transformImg(TextView t, Pattern p, Context c){
		 SpannableString s = SpannableString.valueOf(t.getText());

	        if (addImg(t, s, p, c)) {
	            t.setText(s);
	        }
	}
	
	public static final boolean addImg(TextView t, Spannable s, Pattern p, Context c) {
        boolean hasMatches = false;
        Matcher m = p.matcher(s);

        while (m.find()) {
            int start = m.start();
            int end = m.end();
            hasMatches = true;
            String content = s.toString().substring(start, end);
            //add you custom code here
            //example : replace the text into a picture
            if("[smile]".equals(content)){
            	Drawable d = c.getResources().getDrawable(R.drawable.smile);
            	int a = (int)t.getTextSize();
            	d.setBounds(0, 0, a + 1, a + 1);
            	ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
            	s.setSpan(span, start, end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }

        return hasMatches;
    }

	
}
