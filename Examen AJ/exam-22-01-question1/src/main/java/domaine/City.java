package domaine;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class City {

    // City id -> column CD_REFNIS
    private int id;
    // FR city name -> column tx_descr_fr (no need to keep NL city name)
    private String name;

    private final Set<Firstname> firstnames = new HashSet<Firstname>();

    public City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Firstname> getFirstnames() {
        return Collections.unmodifiableSet(firstnames);
    }

    public boolean addFirstname(Firstname firstname) {
        return this.firstnames.add(firstname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
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
