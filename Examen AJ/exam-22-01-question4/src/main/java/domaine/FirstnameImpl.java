package domaine;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FirstnameImpl implements Firstname {

    private String name;
    private String gender;

    private Map<CityImpl, Integer> counts = new HashMap<CityImpl, Integer>();
    
    
    public FirstnameImpl(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public FirstnameImpl() {
        
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
    public String getGender() {
        return gender;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public Map<CityImpl, Integer> getCounts() {
        return counts;
    }

    @Override
    public void addCount(CityImpl city, int count) {
        this.counts.put(city, count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstnameImpl firstname = (FirstnameImpl) o;
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
