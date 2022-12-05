package database;

public class Queries {
    public static final String CREATE_SCHEMA = "CREATE SCHEMA IF NOT EXISTS project; SET search_path = project;\n";
    public static final String CREATE_USERS = "CREATE TABLE IF NOT EXISTS users (username VARCHAR PRIMARY KEY," +
            "hashed_password VARCHAR NOT NULL, salt VARCHAR NOT NULL);";
    public static final String CREATE_IMAGES = "CREATE TABLE IF NOT EXISTS images (name TEXT, encoding TEXT, time TEXT," +
            "PRIMARY KEY(name, time));";

    public static final String CREATE_MESS = "CREATE TABLE IF NOT EXISTS messages (username TEXT, time TEXT, message TEXT," +
            "PRIMARY KEY(username, time, message));";

    public static final String CREATE = CREATE_SCHEMA + CREATE_USERS + CREATE_IMAGES + CREATE_MESS;
    public static final String GET_USER = "SELECT u.username, u.hashed_password, u.salt FROM project.users u WHERE u.username = ?;";
    public static final String SET_USER_INFO_PASSWORD = "UPDATE users SET hashed_password = ?, salt = ?  WHERE username = ?;";
    public static final String SET_USER_INFO = "UPDATE users SET username = ?  WHERE username = ?;";
    public static final String INSERT_USER = "INSERT INTO users (username, hashed_password, salt) VALUES (?, ?, ?);";

    public static final String INSERT_IMAGE = "INSERT INTO images (name, encoding, time) VALUES (?, ?, ?)";

    public static final String GET_IMAGES = "SELECT name, encoding, time FROM images LIMIT 7";

    public static final String INSERT_MESSAGE = "INSERT INTO messages (username, time, message) VALUES (?, ?, ?)";

    public static final String GET_MESSAGES = "SELECT username, time, message FROM messages LIMIT 100";

    public static final String DELETE_USER = "DELETE FROM users WHERE username = ?";
}
