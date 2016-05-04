package com.rssuser;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.webkit.URLUtil;

public class Exceptions {

	
	public boolean isUrlValid(String url){
		if(URLUtil.isValidUrl(url))return true;
		else return false;
	}
	
	public boolean isOnline(Context c) {
	    ConnectivityManager cm =
	        (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    return netInfo != null && netInfo.isConnectedOrConnecting();
	}
}
