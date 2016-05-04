package com.rssuser;



import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	DatabaseHelper helper;
	Contact c;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		helper = new DatabaseHelper(this);
	}

	public void LoginOnClick(View v){
		if(v.getId() == R.id.ULoginButton)
        {
            EditText a = (EditText)findViewById(R.id.ULoginUserName);
            String str = a.getText().toString();
            EditText b = (EditText)findViewById(R.id.ULoginUserPassword);
            String pass = b.getText().toString();

            String password = helper.searchPass(str);
            
            if(pass.equals(password))
            {
                Intent i = new Intent(MainActivity.this, RSSDisplayActivity.class);
                i.putExtra("Username",str);
                
                
                /*
                 //To Free DATABASE
                  Contact c = helper.getlink(str);
                  c.setLink("");
                  helper.updateLink(c);*/
                
                /*
                //Set some Links
                Contact c = helper.getlink(str);
                c.setLink(name());
                helper.updateLink(c);*/
                
                helper.close();
                startActivity(i);
                finish();
                
            }
            else
            {
                Toast temp = Toast.makeText(MainActivity.this , "Username and password don't match!" , Toast.LENGTH_SHORT);
                temp.show();
            }
    		


        }
        if(v.getId() == R.id.LoginSignUpButton)
        {
            Intent i = new Intent(MainActivity.this, Signup.class);
            startActivity(i);
        }
        overridePendingTransition(R.animator.animation1, R.animator.animation2);
	}
	 String name() {
		ArrayList<String> Link = new ArrayList<String>();
		
		Link.add("http://www.mans.edu.eg/mans-news?format=feed&type=rss");
		Link.add("http://www.arageek.com/feed");
		Link.add("http://www.filgoal.com/arabic/rss/rss.xml");
		Link.add("http://www.sciencemag.org/rss/news_current.xml");
		
		Convert cnv = new Convert();
		String[] lin = cnv.convertArrayListToStringArray(Link);
		String str = cnv.convertArrayToString(lin);
		
		return str;
		
	}

}
