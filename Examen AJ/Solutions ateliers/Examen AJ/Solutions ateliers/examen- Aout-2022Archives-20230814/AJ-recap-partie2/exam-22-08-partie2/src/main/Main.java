package main;

import domain.Request;
import domain.RequestFactory;
import domain.RequestFactoryImpl;

public class Main {

    public static void main(String[] args) {
        RequestFactory factory= new RequestFactoryImpl();
        Server server = new Server(factory);
        server.listenKeyboard();
    }

}
