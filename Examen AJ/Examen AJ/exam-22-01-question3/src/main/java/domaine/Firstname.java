package domaine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Firstname {

    private String name;
    private String gender;

    private Map<City, Integer> counts = new HashMap<City, Integer>();

    public Firstname(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Map<City, Integer> getCounts() {
        return counts;
    }

    public void addCount(City city, int count) {
        this.counts.put(city, count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Firstname firstname = (Firstname) o;
        return Objects.equals(name, firstname.name) && Objects.equals(gender, firstname.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender);
    }

    @Override
    public String toString() {
        return "Firstname{name='" + name + "', gender='" + gender + "'}";
    }

}
