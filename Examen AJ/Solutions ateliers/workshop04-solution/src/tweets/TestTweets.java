package tweets;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestTweets {


    private static List<Tweet> tweets;

    public static void main(String[] args) {

        User jean = new User("@sniperz", "Jean Guy", "Bruxelles");
        User myriam = new User("@mimi14", "Myriam Lois", "Namur");
        User tina = new User("@ting#12", "Tina Nguyen", "Bruxelles");
        User jorge = new User("@pesquej", "Jorge Abasolo", "Bruges");

        tweets = Arrays.asList(
            new Tweet("En plein Atelier Java oklm", LocalDateTime.now(), jean, 21),
            new Tweet("Venez voir ma nouvelle collection de figurines sur https://necliquezpassurcelien.be", LocalDateTime.of(2021, 9, 15, 23, 14), myriam, 2341),
            new Tweet("Pourquoi on doit toujours commencer les cours à 8h30 ? :(", LocalDateTime.of(2021, 8, 23, 14, 52), tina, 24),
            new Tweet("El pescador esta en el bote", LocalDateTime.of(2021, 9, 20, 7, 12), jorge, 367),
            new Tweet("Heureusement que j'ai lu la théorie avant *winkn wink*", LocalDateTime.now().plusMinutes(35), jean, 34),
            new Tweet("Ce soir à 22h30, live peinture avec @ting#1247!!!", LocalDateTime.of(2021, 8, 29, 17, 23), myriam, 1546),
            new Tweet("Donde esta mi navo?", LocalDateTime.of(2021, 9, 20, 7, 22), jorge, 461)
        );

        tweetsABruxelles();
        tweetsEnSeptembre2021Chronologique();
        retweet2000Plus();
        tweetsUtilisateurBruxellesParRetweet();
        textesOrdreAlphabetique();
        tagsOrdreAlphabetique();
        tweetLePlusRetweete();
        nombreTweetsPlusQue100Retweets();
    }


    /**
     * Print une liste de tous les tweets dont l'auteur se trouve à Bruxelles
     */
    private static void tweetsABruxelles() {
        System.out.println(
                tweets.stream()
                        .filter( t -> t.getAuthor().getLocation().equals("Bruxelles"))
                        .collect(Collectors.toList()));
    }

    /**
     * print une liste de tous les tweets publiés en septembre 2021, triès par ordre chronologique
     */
    private static void tweetsEnSeptembre2021Chronologique() {
        System.out.println(
                tweets.stream()
                        .filter(t -> t.getPublicationDate().getMonth().equals(Month.SEPTEMBER) && t.getPublicationDate().getYear() == 2021)
                        .sorted(Comparator.comparing(Tweet::getPublicationDate))
                        .collect(Collectors.toList()));
    }

    /**
     * Print true si au moins un tweet a été retweeté 2000 fois ou +, false sinon
     */
    private static void retweet2000Plus() {
        System.out.println(tweets.stream().anyMatch(t -> t.getRetweets() >= 2000));
    }

    /**
     * Print une liste de tous les tweet dont le user est à Bruxelles, triés par nombre
     * croissant de retweets
     */
    private static void tweetsUtilisateurBruxellesParRetweet() {
        System.out.println(
                tweets.stream()
                        .filter(t -> t.getAuthor().getLocation().equals("Bruxelles"))
                        .sorted(Comparator.comparing(Tweet::getRetweets))
                        .collect(Collectors.toList()));
    }

    /**
     * Print tous les textes des tweets (dans une List<String>) dans l'ordre alphabétique
     */
    private static void textesOrdreAlphabetique(){
        System.out.println(
                tweets.stream()
                        .map(Tweet::getText)
                        .sorted()
                        .collect(Collectors.toList()));
    }

    /**
     * Print le tweet le plus retweeté
     */
    private static void tweetLePlusRetweete() {
        System.out.println(tweets.stream().max(Comparator.comparing(Tweet::getRetweets)).get());
    }

    /**
     * 1. Print une String contenant tous les tags (chaque tag une fois) par ordre alphabétique,
     *    tous séparés par une virgule.
     * 2. même chose en retirant les @ en début de tag
     */
    private static void tagsOrdreAlphabetique() {
        //Partie 1.
        System.out.println(
                tweets.stream()
                        .map(t -> t.getAuthor().getTag())
                        .distinct()
                        .sorted()
                        .collect(Collectors.joining(", ")));

        //Partie 2.
        System.out.println(
                tweets.stream()
                        .map(t -> t.getAuthor().getTag())
                        .distinct()
                        .sorted()
                        .collect(Collectors.joining(", ")));
    }

    /**
     * Print le nombre de tweets qui ont été retweetés plus de 100 fois
     */
    private static void nombreTweetsPlusQue100Retweets() {
        System.out.println(
                tweets.stream()
                        .filter(t -> t.getRetweets() > 100)
                        .count());
    }


}
