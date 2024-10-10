package be.vinci.tests;

import be.vinci.runner.Test;

public class FakeTest2 {

    @Test
    public void test1() {
        System.out.println("FakeTest2 - test1 - OK");
    }

    @Test
    public void test2() {
        System.out.println("FakeTest2 - test2 - OK");
    }

    public void test3() {
        System.out.println("FakeTest2 - test3 - KO - Must not be executed, because no @Test annotation.");
    }

}