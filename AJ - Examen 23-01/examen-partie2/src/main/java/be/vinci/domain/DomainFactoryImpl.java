package be.vinci.domain;

public class DomainFactoryImpl implements DomainFactory {
  @Override
  public TestClass testclass(){
    return new TestClassImpl();
  }
  @Override
  public TestMethod testMethod(){
    return new TestMethodImpl();

  }
}
