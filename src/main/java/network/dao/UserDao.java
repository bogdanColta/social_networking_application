package network.dao;

import database.Database;
import database.Queries;
import network.model.HashedUserCredentials;
import network.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import static utils.SecurityCheck.hashSaltFromPassword;
import static utils.SecurityCheck.passwordsEqual;

public class UserDao {

    public static void setUserInfo(User userInfo, String username) throws SQLException {
        Database.update(Queries.SET_USER_INFO, userInfo.getUsername(), username);
        String[] hashWithSalt = hashSaltFromPassword(userInfo.getPassword());
        assert hashWithSalt != null;
        Database.update(Queries.SET_USER_INFO_PASSWORD, hashWithSalt[0], hashWithSalt[1], userInfo.getUsername());
    }

    public static void addUser(String username, String[] hashWithSalt) throws SQLException {
        Database.update(Queries.INSERT_USER, username, hashWithSalt[0], hashWithSalt[1]);
    }

    public static HashedUserCredentials getUser(String userName) throws SQLException {
        ResultSet resultSet = Database.getResultSet(Queries.GET_USER, userName);
        if (resultSet.next()) {
            String password = resultSet.getString("hashed_password");
            String salt = resultSet.getString("salt");
            return new HashedUserCredentials(password, salt);
        } else {
            return null;
        }
    }

    public static boolean registerUser(String userName, String passwordPlain) throws SQLException {
        HashedUserCredentials user = getUser(userName);
        if (user != null) {
            return false;
        } else {
            String[] hashWithSalt = hashSaltFromPassword(passwordPlain);
            addUser(userName, hashWithSalt);
            return true;
        }
    }

    public static boolean checkUser(String userName, String plainPassword) throws SQLException {
        try {
            HashedUserCredentials credentials = getUser(userName);
            String saltHexStr = credentials.getSalt();
            String hashPswrdHexStr = credentials.getHashed_password();
            return passwordsEqual(plainPassword, saltHexStr, hashPswrdHexStr);
        } catch (NullPointerException nullUser) {
            return false;
        }

    }

    public static void deleteUser(String username) throws SQLException {
        Database.update(Queries.DELETE_USER, username);
    }
}


