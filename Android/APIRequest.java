package com.ptisp;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.BasicResponseHandler;
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

			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = client.execute(req, responseHandler);

			Log.d("PTISP", "BODY: " + responseBody);

			try {
				return new JSONObject(responseBody);
			} catch (Exception e) {
			}
		} catch (Throwable t) {
			Log.e("PTISP", "EXCEPTION in updateStatus()", t);
		}
		return null;
	}

}
