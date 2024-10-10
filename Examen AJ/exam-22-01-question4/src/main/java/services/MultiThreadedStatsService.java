package services;


import domaine.FirstnameImpl;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class MultiThreadedStatsService {

    private Collection<FirstnameImpl> firstnames;

    //TODO : add collection to record calculation results.
    // The results should be ordered from the highest to the lowest count.
    // Note that you could use the "CalculationResult" nested class further below

    public MultiThreadedStatsService(Collection<FirstnameImpl> firstnames) {
        this.firstnames = firstnames;
    }

    /**
     * This methods calculates sequentially for each firstname its total number of occurrences
     * in Belgium and records those results.
     * When all calculations have been performed, this method prints
     * - the highest count
     * - the firstname(s) associated to the highest count,
     * - the duration of the calculations.
     */
    public void syncPrintFirstnameMaxCount() {
        System.out.println("\nStart synchroneous calculation");

        Instant start = Instant.now();

        //TODO : Start to clear your collection of calculation results.
        // Then, sequentially, for each firstname given in firstnames,
        // calculate the total number of occurrences of this name in Belgium.
        // Get the total number of occurrences of a firstname in BE by calling getCount
        // Use those results to feed your collection of calculation results.


        int maxOccurrency = findMaxCountFromCalculationsResults(); // TODO complete this method
        List<String> mostUsedFirstnames = findFirstnamesFromCalculationsResults(maxOccurrency); // TODO complete these method

        Instant end = Instant.now();

        System.out.println("Firstname(s) the most given in BE : " + mostUsedFirstnames);
        System.out.println("Total number of occurrences : " + maxOccurrency);
        System.out.println("Duration to perform this calculation : " + Duration.between(start, end).toMillis() + " ms");
    }

    /**
     * Asynchronously (think threads) calculates for each firstname its total number of occurrences
     * in Belgium and records those results.
     * When all calculations have been performed, this method prints
     * - the highest count
     * - the firstname(s) associated to the highest count,
     * - the duration of the calculations.
     */
    public void asyncPrintFirstnameMaxCount() {
        System.out.println("\nStart asynchronous calculation");

        Instant start = Instant.now();

        //TODO : Start to clear your collection of calculation results.
        // Then asynchronously (think threads), for each firstname given in firstnames,
        // calculate the total number of occurrences of this name in Belgium.
        // Get the total number of occurrences of a firstname in BE by calling getCount.
        // Use those results to feed your collection of calculation results.

        int maxOccurrence = findMaxCountFromCalculationsResults(); // TODO complete this method
        List<String> mostUsedFirstnames = findFirstnamesFromCalculationsResults(maxOccurrence); // TODO complete this method

        Instant end = Instant.now();

        // TODO : Uncomment the following when your method is complete
        /*
        System.out.println("Firstname(s) the most given in BE : " + mostUsedFirstnames);
        //System.out.println("Total number of occurrences : " + maxOccurrence);
        System.out.println("Duration to perform this calculation : " + Duration.between(start, end).toMillis() + " ms");
        */
    }

    /**
     * Count the number of occurrences of a firstname in the stream.
     *
     * @return the number of occurrences of a firstname in the stream
     */
    private int getCount(String firstname) {
        return this.firstnames.stream().filter(firstname1 -> firstname1.getName().equals(firstname))
                .map(f -> f.getCounts().values().stream().reduce(0, Integer::sum))
                .findFirst().orElse(0);
    }

    /**
     * From the collection that records all calculation results, return the highest count
     * (= the highest number of occurrences of a firstname in BE)
     *
     * @return the highest count given in the collection
     */
    private int findMaxCountFromCalculationsResults() {
        //TODO (see the documentation)
        return 0;
    }

    /**
     * From the collection that records all calculation results, and from the requested
     * number of occurrences, provide all associated firstnames. Multiple firstnames could
     * have the same number of occurrences.
     *
     * @param occurrences : number of occurrences a firstname appears
     * @return all firstnames associated to a number of occurrence
     */
    private List<String> findFirstnamesFromCalculationsResults(int occurrences) {
        //Todo (see the documentation)
        return null;
    }

    //TODO optional : you could complete this internal class to define and use a Calculation Result
    // One calculation result is the count (or number of occurrences) of a firstname in BE
    private class CalculationResult {
    }

    //TODO complete this internal class to deal with
    // the asynchronous calculation of a number of occurrences of a firstname in BE
    // the recording of this calculation result
    private class CalculationWorker extends Thread {

    }

}
