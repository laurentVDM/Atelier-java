package be.vinci.main;

import be.vinci.domain.FactoryDomain;
import be.vinci.domain.FactoryDomainImpl;
import be.vinci.runner.TestClassRunner;

public class Main {

    public static void main(String[] args) {
        FactoryDomain factoryDomain= new FactoryDomainImpl();
        TestClassRunner testClassRunner = new TestClassRunner(factoryDomain);
        testClassRunner.runTests();
    }

}