package domaine;

import java.util.Set;

public interface City {

  int getId();

  void setId(int id);

  String getName();

  void setName(String name);

  Set<FirstnameImpl> getFirstnames();

  boolean addFirstname(FirstnameImpl firstname);

  @Override
  boolean equals(Object o);

  @Override
  int hashCode();

  @Override
  String toString();
}
