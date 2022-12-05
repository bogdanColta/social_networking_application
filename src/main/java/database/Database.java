package database;

import database.credentials.Credentials;

import java.sql.*;

public class Database {

    static final String SCHEMA = "?currentSchema=project";
    private static final Connection connection = connect();

    public static Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection ret = DriverManager.getConnection(Credentials.URL + Credentials.USERNAME + SCHEMA, Credentials.USERNAME, Credentials.PASSWORD);

            try (PreparedStatement statement = ret.prepareStatement(Queries.CREATE)) {
                statement.executeUpdate();
            }

            System.out.println("Database connection succeeded!");

            return ret;
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Database connection failed!");
            return null;
        }
    }

    public static void update(String query, String... params) throws SQLException {
        assert connection != null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setString(i + 1, params[i]);
            }
            preparedStatement.executeUpdate();
        }
    }


    public static ResultSet getResultSet(String query, String... params) throws SQLException {
        assert connection != null;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setString(i + 1, params[i]);
        }
        return preparedStatement.executeQuery();
    }

}
