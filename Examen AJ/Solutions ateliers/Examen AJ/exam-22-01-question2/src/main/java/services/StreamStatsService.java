package services;

import domaine.City;
import domaine.Firstname;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamStatsService extends Thread {

    private Supplier<Stream<Firstname>> namesSupplier;
    private Supplier<Stream<City>> citiesSupplier;

    public StreamStatsService(Collection<Firstname> firstnames, Collection<City> cities) {
        this.namesSupplier = firstnames::stream;
        this.citiesSupplier = cities::stream;
    }

    /**
     * Counts the number of firstnames, independent of the city. In short, counts the number of firstnames in the stream.
     *
     * @return the number of firstnames in the stream
     */
    public int getFirstnamesCount() {
        return (int) namesSupplier.get().count();
    }

    /**
     * Sum the count of each firstname to have all the occurrences of all the firstnames
     *
     * @return the number of firstnames occurrences
     */
    public int getFirstnamesOccurrencesCount() {
        return namesSupplier.get().map(f -> f.getCounts().values().stream().reduce(0, Integer::sum)).reduce(0, Integer::sum);
    }

    /**
     * Get all the firstnames (only the name field string) starting with the prefix given in parameter.
     * The check must be case-insensitive.
     *
     * @param prefix the prefix the names must start with.
     * @return a list of firstnames (only the name field string)
     */
    public List<String> getFirstnamesStartingWith(String prefix) {
        // TODO question 2
        return null;
    }

    /**
     * Get the longest firstname (only name field string).
     *
     * @return the longest firstname (only the name field string)
     */
    public String getLongestFirstname() {
        // TODO question 2
        return null;
    }

    /**
     * Get a list of the @max longest firstnames (the object). If there are multiple firstnames with the same length,
     * takes only the first ones to have maximum @max names.
     * If there is less than @max such names, the returned list can be shorter than @max, or even be empty.
     *
     * @param max the number of firstnames
     * @return a list of @max Firstname objects with the longest name
     */
    public List<Firstname> getLongestFirstnames(int max) {
        // TODO question 2
        return null;
    }

    /**
     * Finds the city with the most different female firstnames. The methods MUST throw an IllegalStateException
     * if it can't find such a city.
     *
     * @return the city with the most different female names in Belgium.
     */
    private City cityWithMaxFemaleNames() {
        // TODO question 2
        return null;
    }

    /**
     * Finds the (at most) 5 names starting with the prefix in parameter that have the lowest occurrences in Belgium.
     * If there is less than 5 such names, the returned list can be shorter than 5, or even be empty.
     * The check must be case-insensitive.
     *
     * @param prefix The prefix the names must start with.
     * @return A List containing the top 5 names starting with @prefix
     */
    private List<String> top5NamesStartingWithPrefixLowestOccur(String prefix) {
        // TODO question 2
        return null;
    }

    /**
     * Creates a partition of the cities according to their number of different firstnames by genre. The resulting map must contain a key true
     * paired with the number of cities with more male firstnames than female firstnames, and a key false taht contains the number of cities
     * with more female firstnames than male firstnames.
     *
     * @return A Map containing the described partition.
     */
    private Map<Boolean, Long> numberOfCitiesWithMoreMThanF() {
        // TODO question 2
        return null;
    }

    /**
     * Prints all the stats
     */
    public void printAll() {
        System.out.println("Number of firstnames : " + getFirstnamesCount());
        System.out.println("Number of occurrences of the firstnames : " + getFirstnamesOccurrencesCount() + "\n");

        System.out.println("Firstnames starting with 'ber' : " + getFirstnamesStartingWith("ber"));
        System.out.println("Longest firstname : " + getLongestFirstname());
        System.out.println("10 longest firstnames : " + getLongestFirstnames(10));
        System.out.println("City with the most different female names : " + cityWithMaxFemaleNames());
        System.out.println("Top 5 names starting with the prefix 'Er' with lowest occurrences : " + top5NamesStartingWithPrefixLowestOccur("Er"));
        Map<Boolean, Long> map = numberOfCitiesWithMoreMThanF();
        System.out.println("Number of cities with more different male names than female names : " + (map != null ? map.get(true) : 0));
        System.out.println("Number of cities with more different female names than male names : " + (map != null ? map.get(false) : 0));
    }

    @Override
    public void run() {
        super.run();
    }

}
