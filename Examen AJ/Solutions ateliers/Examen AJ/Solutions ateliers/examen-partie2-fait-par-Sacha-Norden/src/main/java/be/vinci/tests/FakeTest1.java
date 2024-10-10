package be.vinci.tests;

import be.vinci.runner.Test;

public class FakeTest1 {

    @Test
    public void test1() {
        System.out.println("FakeTest1 - test1 - OK");
    }

    @Test
    public void test2() {
        System.out.println("FakeTest1 - test2 - OK");
    }

    public void test3() {
        System.out.println("FakeTest1 - test3 - KO - Must not be executed, because no @Test annotation.");
    }

    @Test
    private void test4() {
        System.out.println("FakeTest1 - test4 - KO - Must not be executed, because not public.");
    }

    @Test
    public String test5() {
        System.out.println("FakeTest1 - test5 - KO - Must not be executed, because do not return void.");
        return null;
    }

    @Test
    public static void test6() {
        System.out.println("FakeTest1 - test6 - KO - Must not be executed, because static.");
    }

}