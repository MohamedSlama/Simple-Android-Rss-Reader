package com.rssuser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void onSignUpClick(View v)
    {
        if(v.getId()== R.id.SignUpButton)
        {
            EditText name = (EditText)findViewById(R.id.SignUpName);
            EditText email = (EditText)findViewById(R.id.SignUpEmail);
            EditText uname = (EditText)findViewById(R.id.SignUpUserName);
            EditText pass1 = (EditText)findViewById(R.id.SignUpPassword);
            EditText pass2 = (EditText)findViewById(R.id.SignUpConfirmPassword);

            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String unamestr = uname.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();
            
            if(!pass1str.equals(pass2str))
            {
                //pop up msg
                Toast.makeText(Signup.this , "Passwords don't match!" , Toast.LENGTH_SHORT).show();
            }
            else if(pass1str.equals("") || namestr.equals("") || emailstr.equals("") || unamestr.equals("")){
            	Toast.makeText(Signup.this , "Some Element missing !!" , Toast.LENGTH_SHORT).show();
            }
            else
            {
                //insert the details in database
                Contact c = new Contact();
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setUname(unamestr);
                c.setPass(pass1str);
                c.setLink("");
                c.setWebsite("");
                if(helper.searchUser(unamestr)){
                	Toast.makeText(Signup.this, "User Found on DataBase", Toast.LENGTH_LONG).show();
                }
                else{
                helper.insertContact(c);
                
                Intent in = new Intent(Signup.this,MainActivity.class);
                startActivity(in);
                finish();
                }
                overridePendingTransition(R.animator.animation1, R.animator.animation2);
            }

        }

    }


}

