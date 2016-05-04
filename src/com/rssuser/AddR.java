package com.rssuser;


public class AddR extends MainActivity{
	DatabaseHelper helper;
	

	public AddR(DatabaseHelper helper){
		this.helper = helper;
	}
	public void add(String Newst,String Website,String UserName){
		Contact c = helper.getlink(UserName);
		String st = c.getLink() + "," + Newst;
		
		String st2 = c.getWebsite() + "," + Website;
		c.setLink(st);
		c.setWebsite(st2);
		helper.updateLink(c);
	}
}
