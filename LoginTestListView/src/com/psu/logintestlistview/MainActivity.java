package com.psu.logintestlistview;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText etUsername;
	private EditText etPassword;
	private Button btnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		InitialWidget();

	}

	private void InitialWidget() {
		etUsername = (EditText) findViewById(R.id.etUsername);
		etPassword = (EditText) findViewById(R.id.etPassword);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		//etUsername.setText("NCMA");
		//etPassword.setText("admin");

		btnLogin.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
	 
				String result = "";
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(
						"http://192.168.0.104/TestLogin/login.aspx?rnd"
								+ Math.random());
				Log.i("URL", "http://192.168.0.104/TestLogin/login.aspx?rnd="
						+ Math.random());
				try {
					ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();
					data.add(new BasicNameValuePair("username", etUsername.getText().toString()));
					data.add(new BasicNameValuePair("password", etPassword.getText().toString()));
					try {
						post.setEntity(new UrlEncodedFormEntity(data,
								HTTP.UTF_8));
						HttpResponse response = client.execute(post);
						result = EntityUtils.toString(response.getEntity(),
								HTTP.UTF_8);
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Log.i("TEST", "5");
					try {
						Log.i("RESULT", "RESULT:" + result);
						JSONObject obj = new JSONObject(result);
						Log.i("RESULT", obj.getString("COUNT"));
						if (obj.getString("COUNT").equals("1")) {
						Intent intent = new Intent(MainActivity.this,IndexActivity.class);
						startActivity(intent);
						} else {
							Toast.makeText(getApplicationContext(),
									"LOGIN FALLED!!", Toast.LENGTH_LONG).show();
						}

					} catch (JSONException e) {
						// TODO: handle exception
						e.printStackTrace();
						Log.i("ERROR", e.getMessage());
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					Log.i("ERROR", ex.getMessage());
				}
				//Toast.makeText(getApplicationContext(), result,
						//Toast.LENGTH_LONG).show();
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
