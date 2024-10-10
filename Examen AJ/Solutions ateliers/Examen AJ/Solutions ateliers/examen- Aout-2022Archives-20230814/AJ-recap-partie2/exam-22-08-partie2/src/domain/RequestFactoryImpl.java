package domain;

public class RequestFactoryImpl implements RequestFactory {
  @Override
  public Request getRequest(){
    return new RequestImpl();
  }

}
