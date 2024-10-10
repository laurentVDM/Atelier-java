package domaine;

public class DomainFactoryImpl implements DomainFactory {

  @Override
  public Firstname getFirstName() {
    return new FirstnameImpl();
  }

  public City getCity() {
    return new CityImpl();
  }
}

