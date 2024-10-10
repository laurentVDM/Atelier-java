package main;

import domaine.CityImpl;
import domaine.DomainFactory;
import domaine.FirstnameImpl;
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

    private Map<Integer, CityImpl> cities = new HashMap<Integer, CityImpl>();
    // Key is : the gender + '#' + the name
    private Map<String, FirstnameImpl> firstnames = new HashMap<String, FirstnameImpl>();

    public void setDomainFactory(DomainFactory domainFactory) {
        this.domainFactory = domainFactory;
    }

    private DomainFactory domainFactory;

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
                CityImpl city = this.cities.get(cityId);
                if (city == null) {
                    city = (CityImpl) domainFactory.getCity();
                    city.setId(cityId);
                    city.setName(attrs[2]);
                    ;
                    this.cities.put(cityId, city);
                }
                // Create firstname or get existing one
                FirstnameImpl firstname = this.firstnames.get(attrs[5] + "#" + attrs[3]);
                if (firstname == null) {
                    firstname = (FirstnameImpl) domainFactory.getFirstName();
                    firstname.setName(attrs[3]);
                    firstname.setGender(attrs[5]);
                    this.firstnames.put(attrs[5] + "#" + attrs[3], firstname);
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
