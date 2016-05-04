package com.rssuser;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class ReadRss extends AsyncTask<Void, Void, Void> {
    Context context;
    ListView list;
    ArrayList<String> address ;
    ArrayList<String> Names;
    ProgressDialog progressDialog;
    URL url;
    ArrayList<FeedItem> AllItems = new ArrayList<FeedItem>();
    public ReadRss(Context context,ListView list,ArrayList<String> Arr,ArrayList<String> Names) {
    	this.list = list;
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        this.address = Arr;
        this.Names = Names;
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
        
        super.onPreExecute();
        
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        
        FeedAdapter adapter  = new FeedAdapter(context, R.layout.feed_screen, AllItems);
        list.setAdapter(adapter);
		list.setOnItemClickListener(new Click(AllItems,context));
		list.setOnScrollListener(new Scroll(context));

    }
    
    
    @Override
	protected Void doInBackground(Void... params) {

    	for (int i =0; i < address.size() ; i++) {
    		ArrayList<FeedItem> temp = new ArrayList<FeedItem> ();
    		temp = ProcessXml(Getdata(address.get(i)),i);
    		if(temp != null){
    			AllItems.addAll(temp);
    		}
		}

    	return null;
			
    }
    
    private ArrayList<FeedItem> ProcessXml(Document data,int num) {
        if (data != null) {
        	ArrayList<FeedItem> feedItems=new ArrayList<FeedItem>();
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);
            NodeList items = channel.getChildNodes();
            for (int i = 0; i < items.getLength(); i++) {
                Node cureentchild = items.item(i);
                if (cureentchild.getNodeName().equalsIgnoreCase("item")) {
                    FeedItem item=new FeedItem();
                    NodeList itemchilds = cureentchild.getChildNodes();
                    for (int j = 0; j < itemchilds.getLength(); j++) {
                        Node cureent = itemchilds.item(j);
                        if (cureent.getNodeName().equalsIgnoreCase("title")){
                            item.setTitle(cureent.getTextContent());
                        }/*else if (cureent.getNodeName().equalsIgnoreCase("description")){
                            item.setDescription(cureent.getTextContent());
                        }*/else if (cureent.getNodeName().equalsIgnoreCase("pubDate")){
                            item.setPubDate(cureent.getTextContent());
                        }else if (cureent.getNodeName().equalsIgnoreCase("link")){
                            item.setLink(cureent.getTextContent());
                        }
                    }
                    item.setName(Names.get(num));
                    feedItems.add(item);
                   // PrintItem("Description", item.getDescription());
                    
                }
            }
            //feedItems
            return feedItems;
        }
        else{
        	return null;
        }
    }
    
    /*public void PrintItem(String Tag,String Element){
    	Log.d(null,Tag +" " + Element);
    }*/

    public Document Getdata(String address) {
        try {
            url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDoc = builder.parse(inputStream);
            
            return xmlDoc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
