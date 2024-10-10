package domaine;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CityImpl implements City {

    // City id -> column CD_REFNIS
    private int id;
    // FR city name -> column tx_descr_fr (no need to keep NL city name)
    private String name;

    private final Set<FirstnameImpl> firstnames = new HashSet<FirstnameImpl>();

    public CityImpl(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CityImpl() {

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Set<FirstnameImpl> getFirstnames() {
        return firstnames;
    }

    @Override
    public boolean addFirstname(FirstnameImpl firstname) {
        return this.firstnames.add(firstname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityImpl city = (CityImpl) o;
        return id == city.id && Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "City{id=" + id + ", name='" + name + "'}";
    }
}
