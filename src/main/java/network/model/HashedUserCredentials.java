package network.model;
public class HashedUserCredentials {

    private String username;
    private String salt;
    private String hashed_password;

    public HashedUserCredentials(){}

    public HashedUserCredentials(String username, String hashed_password, String salt) {
        this.username = username;
        this.hashed_password = hashed_password;
        this.salt = salt;
    }

    public HashedUserCredentials(String hashed_password, String salt) {
        this.hashed_password = hashed_password;
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHashed_password() {
        return hashed_password;
    }

    public void setHashed_password(String hashed_password) {
        this.hashed_password = hashed_password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEmpty(){
        return this.salt ==null || this.hashed_password == null;
    }

}
