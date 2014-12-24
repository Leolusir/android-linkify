package com.weibotextutil.libs;

/* Android textview text-to-link
 * Copyright (c) 2014 leo <leo.lusir@gmail.com>
 * https://github.com/Leolusir  
 * */

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.util.Linkify.MatchFilter;
import android.text.util.Linkify.TransformFilter;
import android.widget.TextView;


public class TextAddLink {
	
	public static final void addLinks(TextView text, Pattern p, String scheme,
            MatchFilter matchFilter, TransformFilter transformFilter) {
        SpannableString s = SpannableString.valueOf(text.getText());

        if (addLinks(s, p, scheme, matchFilter, transformFilter)) {
            text.setText(s);
        }
    }
	
	public static final boolean addLinks(Spannable s, Pattern p,
            String scheme, MatchFilter matchFilter,
            TransformFilter transformFilter) {
        boolean hasMatches = false;
        String prefix = (scheme == null) ? "" : scheme.toLowerCase(Locale.ROOT);
        Matcher m = p.matcher(s);

        while (m.find()) {
            int start = m.start();
            int end = m.end();
            boolean allowed = true;

            if (matchFilter != null) {
                allowed = matchFilter.acceptMatch(s, start, end);
            }

            if (allowed) {
                String url = makeUrl(m.group(0), new String[] { prefix },
                                     m, transformFilter);

                applyLink(url, start, end, s);
                hasMatches = true;
            }
        }

        return hasMatches;
    }
	
	 private static final String makeUrl(String url, String[] prefixes,
	            Matcher m, TransformFilter filter) {
	        if (filter != null) {
	            url = filter.transformUrl(m, url);
	        }

	        boolean hasPrefix = false;
	        
	        for (int i = 0; i < prefixes.length; i++) {
	            if (url.regionMatches(true, 0, prefixes[i], 0,
	                                  prefixes[i].length())) {
	                hasPrefix = true;

	                // Fix capitalization if necessary
	                if (!url.regionMatches(false, 0, prefixes[i], 0,
	                                       prefixes[i].length())) {
	                    url = prefixes[i] + url.substring(prefixes[i].length());
	                }

	                break;
	            }
	        }

	        if (!hasPrefix) {
	            url = prefixes[0] + url;
	        }

	        return url;
	    }
	
	private static final void applyLink(String url, int start, int end, Spannable text) {
		//Remove the underscore
		URLSpanNoUnderline span = new URLSpanNoUnderline(url);

        text.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
	
}
