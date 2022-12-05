package network.model;

public class Image {
    String name;
    String encoding;
    String time;

    public Image(){
    }

    public Image(String name, String encoding, String time){
        this.name = name;
        this.encoding = encoding;
        this.time = time;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }
}
