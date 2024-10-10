package tweets;

public class User {

    private String tag;
    private String fullName;
    private String location;

    public User(String tag, String fullName, String location) {
        this.tag = tag;
        this.fullName = fullName;
        this.location = location;
    }

    public String getTag() {
        return tag;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "User{" +
                "tag='" + tag + '\'' +
                ", fullName='" + fullName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
