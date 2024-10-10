package be.vinci.main;

import be.vinci.domain.DomaineFactory;
import be.vinci.runner.TestClassRunner;

public class Main {


    public static void main(String[] args) {
        DomaineFactory domaineFactory = new DomaineFactory();
        TestClassRunner testClassRunner = new TestClassRunner();
        testClassRunner.setDomaineFactory(domaineFactory);
        testClassRunner.runTests();
    }

}