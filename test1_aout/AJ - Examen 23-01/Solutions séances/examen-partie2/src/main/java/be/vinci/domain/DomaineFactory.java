package be.vinci.domain;

public class DomaineFactory {
    public TestClass getTestClass(){
        return new TestClassImpl();
    }

    public TestMethod getTestMethod(){
        return new TestMethodImpl();
    }
}
