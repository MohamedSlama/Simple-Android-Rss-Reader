package com.rssuser;

import java.util.ArrayList;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class Click implements OnItemClickListener {

	ArrayList<FeedItem> listItems;
	Context activity;
	
	public Click(ArrayList<FeedItem> aListItems, Context anActivity) {
		listItems = aListItems;
		activity  = anActivity;
	}
	
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(listItems.get(pos).getLink()));
		activity.startActivity(i);
	}
	
}