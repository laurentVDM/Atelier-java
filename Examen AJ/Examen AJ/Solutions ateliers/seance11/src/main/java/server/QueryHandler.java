package server;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import domaine.Query;
import domaine.Query.QueryMethod;

public class QueryHandler extends Thread {
	
	private Query query;

	public QueryHandler(Query query) {
		this.query = query;
	}

	@Override
	public void run() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpUriRequest request = null;
		if (this.query.getMethod() == QueryMethod.GET) {
			request = new HttpGet(this.query.getUrl());
		}
		try (CloseableHttpResponse response = httpclient.execute(request)) {
		    System.out.println(response.getStatusLine());
		    HttpEntity entity1 = response.getEntity();
		    System.out.println(EntityUtils.toString(entity1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
