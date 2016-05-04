package com.rssuser;

public class Contact {
    String name ,email,uname,pass,link,website;
    public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	int id;
    
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return this.email;
    }
    public void setUname(String uname)
    {
        this.uname = uname;
    }
    public String getUname()
    {
        return this.uname;
    }
    public void setPass(String pass)
    {
        this.pass = pass;
    }
    public String getPass()
    {
        return this.pass;
    }

}
