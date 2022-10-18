package example;

public class Message {
    private final String ID;
    private final String value;

    public Message(String ID, String value) {
        this.ID = ID;
        this.value = value;
    }

    public String getID() {
        return ID;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{" +
                "ID='" + ID + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
