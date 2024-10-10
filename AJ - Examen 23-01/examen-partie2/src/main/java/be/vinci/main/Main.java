package be.vinci.main;

import be.vinci.domain.DomainFactory;
import be.vinci.domain.DomainFactoryImpl;
import be.vinci.runner.TestClassRunner;

public class Main {

    public static void main(String[] args) {
        DomainFactory domainFactory = new DomainFactoryImpl();
        TestClassRunner testClassRunner = new TestClassRunner(domainFactory);
        testClassRunner.runTests();
    }

}