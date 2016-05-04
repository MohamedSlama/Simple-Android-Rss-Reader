package com.rssuser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewRSSActivity extends Activity {


	EditText tx,nm;
	AddR add;
	DatabaseHelper db;
	Button btn;
	String st;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_new_rss);
		
		st = getIntent().getStringExtra("YourAccount");
		db = new DatabaseHelper(this);
		add = new AddR(db);
		
		
		
		
		tx = (EditText)findViewById(R.id.TxtAddNewRSS);
		nm = (EditText)findViewById(R.id.TxtNameAddNewRss);
		btn = (Button)findViewById(R.id.BtnAddNewRSS);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(tx.getText().toString().equals("") || nm.getText().toString().equals("")){
					Toast.makeText(AddNewRSSActivity.this , "Some Element missing !!" , Toast.LENGTH_SHORT).show();
				}
				else{
				Exceptions e = new Exceptions();
				
				if(e.isUrlValid(tx.getText().toString())){
				add.add(tx.getText().toString(),nm.getText().toString(), st);
				Intent iadd = new Intent(AddNewRSSActivity.this, RSSDisplayActivity.class);
                iadd.putExtra("Username",st);
				startActivity(iadd);
				finish();
				overridePendingTransition(R.animator.animation1, R.animator.animation2);
				}
				else
				{
					Toast.makeText(AddNewRSSActivity.this, "No Valid URL ..", Toast.LENGTH_LONG).show();
				}
				}
			}
		});
				
	}

	

}
