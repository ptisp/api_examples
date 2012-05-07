package com.ptisp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.util.Log;

public class APIRequest {
	private String url = null;
	private String username = null;
	private String password = null;

	public APIRequest(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public void setURL(String url) {
		this.url = url;
	}

	public JSONObject execute() {
		return execute(false);
	}

	public JSONObject execute(boolean isPost) {
		try {
			HttpClient client = new DefaultHttpClient();

			String base64EncodedCredentials = Base64.encodeBytes((username
					+ ":" + password).getBytes());

			HttpRequestBase req;
			if (isPost) {
				req = new HttpPost(url);
			} else {
				req = new HttpGet(url);
			}

			req.addHeader("Authorization", "Basic " + base64EncodedCredentials);

			HttpResponse response = client.execute(req);

			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();

			String respondebody = convertStreamToString(is);

			Log.d("PTISP", "BODY: " + respondebody);

			try {
				return new JSONObject(respondebody);
			} catch (Exception e) {
			}
		} catch (Throwable t) {
			Log.e("PTISP", "EXCEPTION in APIRequest", t);
		}
		return null;
	}

	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append((line + "\n"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

}