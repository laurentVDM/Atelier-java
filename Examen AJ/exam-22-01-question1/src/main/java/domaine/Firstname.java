package domaine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Firstname {

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
        return Collections.unmodifiableMap(counts);
    }

    public void addCount(City city, int count) {
        this.counts.put(city, count);
    }

    //Ajoutez dans la classe FirstName une méthode renvoyant toutes les villes dans lesquelles
    //ce prénom a été donné trié par ordre alphabétique du nom (name) de la ville et, en cas
    //d’égalité de nom, sur le code postal (id).
    public ArrayList villesPrenom(String name) {
        ArrayList<City> villes = new ArrayList<>();
        for(City c : counts.keySet()){
            if(c.getFirstnames().contains(name)){
                villes.add(c);
            }
        }
        return villes;
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

    public enum Gender {
        M("masculin"), F("feminin");

        private String abreviation;

        Gender(String abreviation) {
            this.abreviation = abreviation;
        }

        @Override
        public String toString() {
            return abreviation;
        }
    }

}
