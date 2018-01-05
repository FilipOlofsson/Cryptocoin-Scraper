import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {
    
    String url;
    String username;
    String password;
    
    Connection connection;
    
    Database(String server, int port, String username, String password, String db) {
        url = "jdbc:mysql://" + server + ":" + port + "/" + db;
        this.username = username;
        this.password = password;
        try {
            connection = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }
    
    void Insert(String coin, double price) {
        String query = "INSERT INTO prices (coin, price, date) VALUES (?, ?, ROUND(UNIX_TIMESTAMP(CURTIME(4)) * " +
                "1000))";
        PreparedStatement prepQuery = null;
        try {
            prepQuery = connection.prepareStatement(query);
            prepQuery.setString(1, coin);
            prepQuery.setDouble(2, price);
            prepQuery.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
