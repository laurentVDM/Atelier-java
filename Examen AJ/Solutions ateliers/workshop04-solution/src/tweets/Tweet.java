package tweets;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tweet {

    private String text;
    private LocalDateTime publicationDate;
    private User author;
    private int retweets;

    public Tweet(String text, LocalDateTime publicationDate, User author, int retweets) {
        this.text = text;
        this.publicationDate = publicationDate;
        this.author = author;
        this.retweets = retweets;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public User getAuthor() {
        return author;
    }

    public int getRetweets() {
        return retweets;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
        return "Tweet" +
                "\ntext='" + text + '\'' +
                "\npublicationDate=" + formatter.format(publicationDate) +
                "\nuser=" + author +
                "\nretweets=" + retweets;
    }
}
