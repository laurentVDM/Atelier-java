package be.vinci.domain;

public class Factory {

    public TestClass getTestClass(){
        return new TestClassImpl();
    }

    public TestMethod getTestMethod(){
        return new TestMethodImpl();
    }
}
