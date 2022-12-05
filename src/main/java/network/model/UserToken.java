package network.model;

import static utils.SecurityCheck.getRandomString;

public class UserToken {

    String username;
    String token;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserToken(String username) {
        this.username = username;
        this.token = getRandomString();
    }

    public UserToken(String username, String token) {
        this.username = username;
        this.token = token;
    }


    public void resetToken() {
        this.token = getRandomString();
    }

}
