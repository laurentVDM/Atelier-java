package main;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import blacklist.BlacklistService;
import blacklist.BlacklistServiceImpl;
import domaine.QueryFactory;
import domaine.QueryFactoryImpl;
import server.ProxyServer;

public class Main {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		QueryFactory queryFactory = new QueryFactoryImpl();
		BlacklistService blacklistService = new BlacklistServiceImpl();
		ProxyServer proxyServer = new ProxyServer(queryFactory, blacklistService);
		proxyServer.startServer();
	}

}