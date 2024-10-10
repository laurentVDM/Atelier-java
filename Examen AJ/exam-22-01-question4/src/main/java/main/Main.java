package main;

import domaine.DomainFactory;
import domaine.DomainFactoryImpl;
import utils.InjectionService;

public class Main {

    public static void main(String[] args) {
        DomainFactory domainFactory = InjectionService.getDependency(DomainFactory.class);

        Client client = new Client();
        client.setDomainFactory(domainFactory);
        client.printStats();
    }

}
