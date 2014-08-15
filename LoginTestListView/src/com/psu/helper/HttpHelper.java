package com.psu.helper;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class HttpHelper {
	public static final String GetString(String url,ArrayList<NameValuePair> data){
		Log.i("HTTPHELPER","GETSTRING");
		String result = "";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url+"?rnd="+Math.random());
        Log.i("URL",post.getURI().toString());
        try {
        	if(data!=null) post.setEntity(new UrlEncodedFormEntity(data,HTTP.UTF_8));
			Log.i("HTTPHELPER","SET ENTITY");
			HttpResponse response = client.execute(post);
			Log.i("TEST","5");
			result= EntityUtils.toString(response.getEntity(),HTTP.UTF_8);
			JSONObject obj;
			Log.i("TEST","6");
			try {
				obj = new JSONObject(result);
				Log.i("RESULT",obj.getString("COUNT"));
				if(obj.getString("COUNT").equals("1")){
				}else{
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.i("ERROR",e.getMessage());
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("ERROR",e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("ERROR",e.getMessage());
		}
		
		return result;
	}}
