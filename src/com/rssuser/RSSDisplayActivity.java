package com.rssuser;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class RSSDisplayActivity extends Activity {

	String username;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rssdisplay);
		username = getIntent().getStringExtra("Username");
		
		DatabaseHelper db = new DatabaseHelper(this);
		Contact c = db.getlink(username);
		String Links = c.getLink();
		String Names = c.getWebsite();
		
		
		Convert cnv = new Convert();
		ArrayList<String> Link = new ArrayList<String>();
		ArrayList<String> WebStieName = new ArrayList<String>();
		String[] lin = cnv.convertStringToArray(Links);
		String[] web = cnv.convertStringToArray(Names);
		Link = cnv.converStringArrayToArrayList(lin);
		WebStieName = cnv.converStringArrayToArrayList(web);
		
		
		Exceptions ex = new Exceptions();
		if(ex.isOnline(this)){
			ReadRss readRss = new ReadRss(RSSDisplayActivity.this,(ListView) findViewById(R.id.LVrssdisplay),Link,WebStieName);
			readRss.execute();
			}
		else{
			Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_new_rs, menu);
		return true;
	}
	
	


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent Aintent = new Intent(RSSDisplayActivity.this,AddNewRSSActivity.class);
			Aintent.putExtra("YourAccount", username);
			startActivity(Aintent);
			overridePendingTransition(R.animator.animation1, R.animator.animation2);
			return true;
		}
		else if(id == R.id.refresh){
			finish();
			startActivity(getIntent());
		}
		return super.onOptionsItemSelected(item);
	}
	
}
