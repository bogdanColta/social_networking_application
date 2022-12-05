package network.model;

public class Message {
    String username;
    String time;
    String message;

    public Message(){
    }

    public Message(String username, String time, String encoding){
        this.username = username;
        this.time = time;
        this.message = encoding;
    }

    public String getTime() {
        return time;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
