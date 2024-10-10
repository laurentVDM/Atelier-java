package be.vinci.domain;

public class FactoryDomainImpl implements FactoryDomain {

  @Override
  public TestMethod getMethod() {
    return new TestMethodImpl();
  }
  public TestClass getTestClass(){
    return new TestClassImpl();
  }

}
