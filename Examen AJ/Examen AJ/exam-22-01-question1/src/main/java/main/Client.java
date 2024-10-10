package main;

import domaine.City;
import domaine.Firstname;
import domaine.Firstname.Gender;
import services.MultiThreadedStatsService;
import services.StreamStatsService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Client {

    private Map<Integer, City> cities = new HashMap<Integer, City>();
    // Key is : the gender + '#' + the name
    private Map<Gender, Firstname> firstnames = new HashMap<Gender, Firstname>();

    /**
     * Reads the CSV file, skip the header (column names), and fill the cities/firstnames maps
     */
    public void readFile() {
        try {
            Path path = Paths.get(Main.class.getClassLoader().getResource("TA_POP_2021.csv").toURI());
            Files.lines(path, Charset.forName("UTF-8")).skip(1).forEach(line -> {
                String[] attrs = line.split(";");
                // Create city, or get the exiting one
                int cityId = Integer.parseInt(attrs[0]);
                City city = this.cities.get(cityId);
                if (city == null) {
                    city = new City(cityId, attrs[2]);
                    this.cities.put(cityId, city);
                }
                // Create firstname or get existing one
                Firstname firstname = this.firstnames.get(attrs[5] + "#" + attrs[3]);
                if (firstname == null) {
                    firstname = new Firstname(attrs[3], Gender.valueOf(attrs[5]));
                    this.firstnames.put(Gender.valueOf(attrs[5] + "#" + attrs[3]), firstname);
                }
                // Link them
                firstname.addCount(city, Integer.parseInt(attrs[4]));
                city.addFirstname(firstname);
            });
        } catch (IOException | URISyntaxException e) {
            //throw new UncheckedIOException(e);
            e.printStackTrace();
        }
    }

    public void printStats() {
        readFile();
        StreamStatsService stats = new StreamStatsService(firstnames.values(), cities.values());
        stats.printAll();

        MultiThreadedStatsService stats2 = new MultiThreadedStatsService(firstnames.values());
        stats2.syncPrintFirstnameMaxCount();
        stats2.asyncPrintFirstnameMaxCount();
    }

}
