package tweets;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TweetDistribution {


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
                new Tweet("Donde esta mi navo?", LocalDateTime.of(2021, 9, 20, 7, 22), jorge, 461),
                new Tweet("Au secours j'aurai jamais le temps", LocalDateTime.now().plusHours(2), jean, 91),
                new Tweet("En septembre je m'achète toutes les figurines que vous aimez :)", LocalDateTime.of(2021, 8, 30, 22, 24), myriam, 1899),
                new Tweet("Vive le métro, je suis presque arrivée à l'heure ajd", LocalDateTime.of(2021, 9, 29, 8, 36), tina, 12),
                new Tweet("Me gustan muchos los jalapenos", LocalDateTime.of(2021, 8, 12, 21, 21), jorge, 458),
                new Tweet("Ah ben si puisque j'ai lu la théorie avant *winkn wink*", LocalDateTime.now().plusHours(2), jean, 121),
                new Tweet("OMG on m'a volé mes figurines :(:(:(", LocalDateTime.of(2021, 9, 30, 23, 12), myriam, 2984)
        );

        System.out.println("Tweets par auteur :");
        tweetsByAuthor().forEach((k, v) -> System.out.println(k + " : " + v));

        System.out.println("\nNombre de tweets par auteur :");
        numberOfTweetByAuthor().forEach((k, v) -> System.out.println(k + " : " + v));

        System.out.println("\ntwetet le plus retweeté par auteur :");
        totalRetweetByAuthor().forEach((k, v) -> System.out.println(k + " : " + v));

        System.out.println("\nTweets dans et hors de bruxelles :");
        tweetsInAndOutBxl().forEach((k, v) -> System.out.println(k + " : " + v));

        System.out.println("\nTweets par visibilité :");
        tweetsByVisibility().forEach((k, v) -> System.out.println(k + " : " + v));

    }

    /**
     * Répartit les tweets selon leurs auteurs
     * @return une map contenant les tweets répartis par auteurs
     */
    private static Map<User, List<Tweet>> tweetsByAuthor() {
        return tweets.stream()
                .collect(Collectors.groupingBy(Tweet::getAuthor));
    }

    /**
     * Calcule le nombre de tweets pour chaque auteur
     * @return une map contenant, pour chaque auteur, le nombre de tweets qu'il ou elle a publiés
     */
    private static Map<User, Long> numberOfTweetByAuthor() {
        return tweets.stream()
                .collect(Collectors.groupingBy(Tweet::getAuthor, Collectors.counting()));
    }

    /**
     * Calcule, pour chaque auteur, le nombre total de retweets
     * @return une map contenant, pour chaque auteur, le nombre total de retweets
     */
    private static Map<User, Integer> totalRetweetByAuthor() {
        return tweets.stream()
                .collect(Collectors.groupingBy(
                        Tweet::getAuthor,
                        Collectors.summingInt(Tweet::getRetweets)));
    }

    /**
     * Répartit les tweets selon la localisation deleur auteur.
     * @return une map contenant, dans la clé true, les tweets dont l'auteur est à Bruxelles, et dans false, les autres tweets.
     */
    private static Map<Boolean, List<Tweet>> tweetsInAndOutBxl() {
        return tweets.stream()
                .collect(Collectors.partitioningBy(
                        t -> t.getAuthor().getLocation().equals("Bruxelles")));
    }

    /**
     * Classe les tweets, en fonction du nombre de retweets, dans différentes catgéories
     * de visibilité.
     * @return une map contenant les tweets regroupés par visibilité
     */
    private static Map<Visibility, List<Tweet>> tweetsByVisibility() {
        return tweets.stream()
                .collect(Collectors.groupingBy(
                        (Tweet t) -> {
                            int rt = t.getRetweets();
                            if(rt >= 2000) return Visibility.FAMOUS;
                            if(rt >= 800) return Visibility.POPULAR;
                            if(rt >= 100) return Visibility.NORMAL;
                            return Visibility.UNKNOWN;
                        })
                );
    }

    /**
     * Enum pour la fonction tweetsByVisibility()
     * FAMOUS : 2000+ retweets
     * POPULAR : 800-1999 retweets
     * NORMAL : 100-799 retweets
     * UNKNOWN : 0-99 retweets
     */
    private enum Visibility {
        FAMOUS,
        POPULAR,
        NORMAL,
        UNKNOWN
    }

}
