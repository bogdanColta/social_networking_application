package network.dao;

import database.Database;
import database.Queries;
import network.model.Message;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {
    public static List<Message> getMessages() throws SQLException {
        ResultSet resultSet = Database.getResultSet(Queries.GET_MESSAGES);
        List<Message> messages = new ArrayList<>();
        while (resultSet.next()){
            String username = resultSet.getString(1);
            String time = resultSet.getString(2);
            String message = resultSet.getString(3);
            Message message1 = new Message(username, time, message);
            messages.add(message1);
        }
        return messages;
    }

    public static void addMessage(String name, String time, String message) throws SQLException {
        Database.update(Queries.INSERT_MESSAGE, name, time, message);
    }
}
