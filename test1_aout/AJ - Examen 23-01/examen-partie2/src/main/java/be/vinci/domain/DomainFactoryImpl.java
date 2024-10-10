package be.vinci.domain;

public class DomainFactoryImpl implements DomainFactory {

  @Override
  public TestMethod getTestMethod(){
    return new TestMethodImpl();
  }
  @Override
  public TestClass getTestClass() {
    return new TestClassImpl();
  }
}
