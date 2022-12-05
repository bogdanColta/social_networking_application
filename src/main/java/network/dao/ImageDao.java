package network.dao;

import database.Database;
import database.Queries;
import network.model.Image;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImageDao {
    public static List<Image> getImages() throws SQLException {
        ResultSet resultSet = Database.getResultSet(Queries.GET_IMAGES);
        List<Image> images = new ArrayList<>();
        while (resultSet.next()){
            String name = resultSet.getString(1);
            String encoding = resultSet.getString(2);
            String time = resultSet.getString(3);
            Image image = new Image(name, encoding, time);
            images.add(image);
        }
        return images;
    }

    public static void addImage(String name, String encoding, String time) throws SQLException {
        Database.update(Queries.INSERT_IMAGE, name, encoding, time);
    }
}
