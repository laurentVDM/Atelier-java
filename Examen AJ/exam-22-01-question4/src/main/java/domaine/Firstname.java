package domaine;

import java.util.Map;

public interface Firstname {

  String getName();

  void setName(String name);

  String getGender();

  void setGender(String gender);

  Map<CityImpl, Integer> getCounts();

  void addCount(CityImpl city, int count);
}
