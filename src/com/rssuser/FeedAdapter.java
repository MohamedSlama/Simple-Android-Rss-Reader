package com.rssuser;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FeedAdapter extends ArrayAdapter<FeedItem> {
	Context m_HostActivity;
	int m_layoutResourceId;
	ArrayList<FeedItem> m_array;
	
	public FeedAdapter(Context context, int resource, ArrayList<FeedItem> objects) {
		super(context, resource, objects);
		
		m_HostActivity = context;
		m_layoutResourceId = resource;
		m_array = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		FeedItem data = m_array.get(position);
		
		if(convertView==null)
		{
			convertView = LayoutInflater.from(m_HostActivity).inflate(m_layoutResourceId, parent,  false);
		}
		
		
		TextView Title = (TextView)convertView.findViewById(R.id.rssDescription),
				//Description= (TextView)convertView.findViewById(R.id.rssdescription),
				Website= (TextView)convertView.findViewById(R.id.RssTitle),
				Date= (TextView)convertView.findViewById(R.id.rssDate);
		
		TextView btn = (TextView) convertView.findViewById(R.id.rssImage);
		String IM = "" +data.getName().charAt(0);
		btn.setText(IM);
		
		Title.setText(data.getTitle());
		Website.setText(data.getName());
		//Description.setText("Description: "+data.getDescription());
		Date.setText("Date: "+data.getPubDate());
		
		return convertView;
	}
	
}
