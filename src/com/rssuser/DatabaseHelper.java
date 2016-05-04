package com.rssuser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_LINK = "link";
    private static final String COLUMN_WEBSITE_NAME = "website";
    

    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null , " +
            "name text not null , email text not null , uname text not null , pass text not null , link text not null , website text not null );";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertContact(Contact c) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query , null);
        int count = cursor.getCount();
        values.put(COLUMN_ID , count);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_UNAME, c.getUname());
        values.put(COLUMN_PASS, c.getPass());
        values.put(COLUMN_LINK, c.getLink());
        values.put(COLUMN_WEBSITE_NAME, c.getWebsite());
        
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    
    
    

    public String searchPass(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname, pass from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query , null);
        String a, b ;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                if(a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        return b;
    }
    
    public boolean searchUser(String uname)
    {
        db = this.getReadableDatabase();
        String query = "select uname from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query , null);
        String a;
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                if(a.equals(uname))
                {
                    return true;
                }
            }
            while(cursor.moveToNext());
        }

        return false;
    }
    
    
	public void  updateLink( Contact n)
	{
		//=============== 3 steps (open, update, close) DB ==============
		
			db = getWritableDatabase();
			
			
	        
			//inserted columns
			ContentValues params= new ContentValues();
			params.put(COLUMN_NAME, n.getName());
			params.put(COLUMN_EMAIL, n.getEmail());
			params.put(COLUMN_LINK, n.getLink());
			params.put(COLUMN_WEBSITE_NAME, n.getWebsite());
			params.put(COLUMN_UNAME, n.getUname());
			params.put(COLUMN_PASS, n.getPass());
			
			db.update(TABLE_NAME, params, "id= ? ", new String[] {""+n.getId()});
			
			db.close();
	}
    public Contact getlink(String uname)
    {
    	Contact c = new Contact();
        db = this.getReadableDatabase();
        String que = "select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(que , null);

        String a;
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(3);

                if(a.equals(uname))
                {
                	c.setId(cursor.getInt(0));
                    c.setName(cursor.getString(1));
                    c.setEmail(cursor.getString(2));
                    c.setUname(cursor.getString(3));
                    c.setPass(cursor.getString(4));
                    c.setLink(cursor.getString(5));
                    c.setWebsite(cursor.getString(6));
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        return c;
    }
    
    
    
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
