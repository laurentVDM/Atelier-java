package server;

import domaine.Query;
import domaine.Query.QueryMethod;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

public class QueryHandler extends Thread {
	
	private Query query;

	public QueryHandler(Query query) {
		this.query = query;
	}

	@Override
	public void run() {
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			HttpUriRequest request = null;
			if (this.query.getMethod() == QueryMethod.GET) {
				request = new HttpGet(this.query.getUrl());
			}
			try (CloseableHttpResponse response = httpclient.execute(request)) {
				System.out.println(response.getCode() + " " + response.getReasonPhrase());
				HttpEntity entity = response.getEntity();
				System.out.println(EntityUtils.toString(entity));
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

}
