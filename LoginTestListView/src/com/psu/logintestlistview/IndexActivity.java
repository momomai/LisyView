package com.psu.logintestlistview;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.psu.helper.HttpHelper;

public class IndexActivity extends Activity{
	private ListView lvData;
	private JSONArray data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_list_view);
		Toast.makeText(getApplicationContext(), "Welcome to FirstPage", Toast.LENGTH_SHORT).show();
		
		
		String result = HttpHelper.GetString("http://192.168.0.104/TestLogin/index.aspx", null);
        Log.i("RESULT",result);
		Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
		
		try {
			data = new JSONArray(result);
			lvData.setAdapter(new LvDataBaseAdapter());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public class LvDataBaseAdapter extends BaseAdapter{
		private Holder holder;
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.length();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if(convertView==null){
				holder = new Holder();
				convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.next_listview_mainactivity, null);
				holder.tvHeader = (TextView) convertView.findViewById(R.id.tvHeader);
				holder.tvHeader.setTextColor(Color.parseColor("#FF0000"));
				holder.tvDescription = (TextView) convertView.findViewById(R.id.tvDescription);
				convertView.setTag(holder);
			}else{
				holder = (Holder) convertView.getTag();
			}
			JSONObject jsObj;
			try {
				jsObj = (JSONObject) data.get(position);
				holder.tvHeader.setText(jsObj.getString("TITLE"));
				holder.tvDescription.setText("DESCRIPTION : "+jsObj.getString("TPI_NO"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return convertView;
		}
		public class Holder {
			public TextView tvHeader;
			public TextView tvDescription;
		}		
	}

}
