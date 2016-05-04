package com.rssuser;

import java.util.ArrayList;

public class Convert {
	public Convert() {}
	public  String convertArrayToString(String[] array){
		String strSeparator = ",";
		String str = "";
    	for (int i = 0;i<array.length; i++) {
    			str = str+array[i];
    			if(i<array.length-1){
    				str = str+strSeparator;
    			}
    	}
    	return str;
	}
	public String[] convertStringToArray(String str){
			String strSeparator = ",";
			String[] arr = str.split(strSeparator);
		return arr;
	}
	public ArrayList<String> converStringArrayToArrayList(String[] str){
		ArrayList<String> arr = new ArrayList<String>();
		for(int i=0;i<str.length;i++){
			arr.add(str[i]);
		}
		
		return arr;
	}
	
	public String[] convertArrayListToStringArray(ArrayList<String> arr){

		String[] Str = new String[arr.size()];
		Str = arr.toArray(Str);

		for(String s : Str)
		    System.out.println(s);
		return Str;
	}

}
