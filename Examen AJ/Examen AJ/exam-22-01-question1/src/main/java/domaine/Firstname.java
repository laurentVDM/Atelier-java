package domaine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;

public class Firstname {

    public enum Gender {
        M("masculin"), F("feminin");

        private String string;
        Gender(String string){
            this.string = string;
        }
        public String getString() {
            return string;
        }

        @Override
        public String toString() {
            return string;
        }
    }

    private String name;
    private Gender gender;

    private Map<City, Integer> counts = new HashMap<City, Integer>();

    public Firstname(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Map<City, Integer> getCounts() {
        Map<City, Integer> x = Collections.unmodifiableMap(counts);
        return x;
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

    public TreeSet<City> citiesGivenName (String name) {
        TreeSet<City> h = new TreeSet<City>(Comparator.comparing(City::getName).thenComparing(City::getId));
        for ( City c : counts.keySet()){
            if(counts.get(c) > 0 ){
                h.add(c);
            }
        }
        return h;
    }



}
